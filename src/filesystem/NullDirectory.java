package filesystem;

class NullDirectory extends Directory {
    NullDirectory() {
        super(true);
    }

    void addItem(Item item) {
        // Prevent things from actually being stored
    }

    void removeItem(Item item) {
        // Prevent things from actually being stored
    }
}
