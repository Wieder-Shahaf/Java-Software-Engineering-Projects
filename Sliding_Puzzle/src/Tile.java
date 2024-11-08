/**
 * Represents a tile in a puzzle, containing a value
 */
public class Tile {
private final int value;
public  Tile (int value){
    this.value=value;
}

    /**
     * Retrieves the value of the tile.
     *
     * @Return The value of the tile
     */
    public int getValue(){
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Tile)) {
            return false;
        }
        Tile tile = (Tile) other;
        return value == tile.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}