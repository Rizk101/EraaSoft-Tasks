public abstract class Club {
    protected String clubName;
    protected Player[] players = new Player[5];
    protected int count = 0;

    public Club(String clubName) {
        this.clubName = clubName;
    }

    public void showPlayers() {
        if (count == 0) {
            System.out.println("No players in " + clubName);
            return;
        }

        System.out.println("Players in " + clubName + ":");

        for (int i = 0; i < count; i++) {
            System.out.println("--------");
            players[i].display();
        }
    }

    public abstract void addPlayer(Player player);
}