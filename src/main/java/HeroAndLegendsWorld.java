public class HeroAndLegendsWorld extends Grid {
    private Player player;
    private int playerPositionX;
    private int playerPositionY;
    public HeroAndLegendsWorld(int size, Player player) {
        super(size);
        this.player = player;
        this.populateGrid();
        this.spawn();
    }

    @Override
    public void populateGrid() {
        for (int i = 0; i < this.getSize(); i++) {
            for (int j = 0; j < this.getSize(); j++) {
                if (ChanceGenerator.generateChance(90)) {
                    this.grid[i][j] = new CommonTile();
                } else {
                    if (ChanceGenerator.generateChance(50)) {
                        this.grid[i][j] = new Market();
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
                ((ReachableTile) this.grid[y][x]).arrive(player);
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
            if (this.grid[y][x].getClass() == Market.class) {
                valid = true;
                this.playerPositionX = x;
                this.playerPositionY = y;
                ((ReachableTile) this.grid[y][x]).arrive(player);
            }
        }
    }
}
