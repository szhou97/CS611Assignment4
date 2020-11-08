public abstract class Grid {
    protected Tile[][] grid;
    private int size;
    public Grid(int size) {
        this.size = size;
        this.grid = new Tile[size][size];
    }

    public abstract void populateGrid();
    public abstract boolean updatePosition(String input);
    public abstract void spawn();
    
    public int getSize() {
        return this.size;
    }

    public void printGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int k = 0; k < this.size; k++) 
                System.out.print("=======");
            System.out.print("\n");
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print("|" + grid[i][j].toString() + "|");
            }
            System.out.print("\n");
        }
        for (int k = 0; k < this.size; k++) 
            System.out.print("=======");
        System.out.print("\n");
    }
}
