public class HeroAndLegendsWorld extends Grid {
    private Player player;
    private int playerPositionX;
    private int playerPositionY;
    private Inventory stock;
    private ElementCollection monsters;
    public HeroAndLegendsWorld(int size, Player player, 
                        Inventory stock, ElementCollection monsters) {
        super(size);
        this.player = player;
        this.stock = stock;
        this.monsters = monsters;
        this.populateGrid();
        this.spawn();
    }

    @Override
    public void populateGrid() {
        for (int i = 0; i < this.getSize(); i++) {
            for (int j = 0; j < this.getSize(); j++) {
                if (ChanceGenerator.generateChance(80)) {
                    this.grid[i][j] = new CommonTile(monsters);
                } else {
                    if (ChanceGenerator.generateChance(20)) {
                        this.grid[i][j] = new Market(stock);
                    } else {
                        this.grid[i][j] = new UnreachableTile();
                    }
                }
            }
        }
    }

    @Override
    public boolean updatePosition(String input) {
        int x = this.playerPositionX, y = this.playerPositionY;
        switch(input) {
            case "w":
                x = this.playerPositionX;
                y = this.playerPositionY - 1;
                break;
            case "a":
                x = this.playerPositionX - 1;
                y = this.playerPositionY;
                break;
            case "s":
                x = this.playerPositionX;
                y = this.playerPositionY + 1;
                break;
            case "d":
                x = this.playerPositionX + 1;
                y = this.playerPositionY;
                break;
        }

        if (0 > x || x >= this.getSize() || 0 > y || y >= this.getSize()) {
            return false;
        } else {
            if (!this.grid[y][x].reachable()) {
                return false;
            } else {
                ((ReachableTile) this.grid[playerPositionY][playerPositionX]).leave();
                ((ReachableTile) this.grid[y][x]).arrive(player, false);
                playerPositionY = y;
                playerPositionX = x;
                return true;
            }
        }
    }

    @Override
    public void spawn() {
        boolean valid = false;
        while(!valid) {
            int x = ChanceGenerator.generateRandomNumber(this.getSize());
            int y = ChanceGenerator.generateRandomNumber(this.getSize());
            if (this.grid[y][x].getClass() == CommonTile.class) {
                valid = true;
                this.playerPositionX = x;
                this.playerPositionY = y;
                ((ReachableTile) this.grid[y][x]).arrive(player, true);
            }
        }
    }
}
