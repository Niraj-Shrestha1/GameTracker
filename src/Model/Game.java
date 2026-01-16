package Model;

public class Game {
    private String title;
    private String platform;
    private int year;
    private String genre;
    
    // Constructor
    public Game(String title, String platform, int year, String genre) {
        this.title = title;
        this.platform = platform;
        this.year = year;
        this.genre = genre;
    }
    
    // Getter methods
    public String getTitle() {
        return title;
    }
    
    public String getPlatform() {
        return platform;
    }
    
    public int getYear() {
        return year;
    }
    
    public String getGenre() {
        return genre;
    }
    
    // Setter methods
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setPlatform(String platform) {
        this.platform = platform;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    // For displaying in table - ADD THIS IF MISSING
    public Object[] toTableRow() {
        return new Object[]{title, platform, year, genre};
    }
    
    // For displaying in combo boxes - ADD THIS
    @Override
    public String toString() {
        return title;
    }
}