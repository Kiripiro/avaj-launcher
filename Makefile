NAME = avajLauncher
EXEC = java
CC = javac
FLAGS = -cp
SRCDIR = src/main/java/com/atourret/avajLauncher
RESDIR = src/main/resources
OUTDIR = build
CLASSDIR = $(OUTDIR)/classes
RESDIR_OUT = $(OUTDIR)/resources
OUTFILE = sources.txt

all: $(NAME)

$(NAME): compile copy-resources run

compile:
	@echo "Compiling Java sources..."
	@find $(SRCDIR) -name "*.java" > $(OUTFILE)
	@mkdir -p $(CLASSDIR)
	@$(CC) -d "$(CLASSDIR)" @$(OUTFILE)
	@echo "Compilation finished."

copy-resources:
	@echo "Copying resources..."
	@mkdir -p $(RESDIR_OUT)
	@cp -r $(RESDIR)/* $(RESDIR_OUT)
	@echo "Resources copied."

run:
	@echo "Running program..."
	@$(EXEC) -classpath "$(CLASSDIR):$(RESDIR_OUT)" com.atourret.avajLauncher.Main scenario.txt

clean:
	@echo "Cleaning compiled files..."
	@rm -rf $(CLASSDIR)/*
	@rm -f $(OUTFILE)
	@echo "Clean finished."

fclean: clean
	@echo "Removing output directory..."
	@rm -rf $(OUTDIR)
	@echo "Full clean finished."

re: fclean all

.PHONY: all compile copy-resources run clean fclean re@
