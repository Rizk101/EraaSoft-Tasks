public class PrivateClub extends Club implements MembershipRules {

    public PrivateClub(String name) {
        super(name);
    }

    @Override
    public boolean canJoin(Player player) {
        return player.getAge() >= 18;
    }

    @Override
    public void addPlayer(Player player) {
        if (!canJoin(player)) {
            System.out.println("Rejected from Private Club");
        } else if (count >= players.length) {
            System.out.println("Private Club is full");
        } else {
            players[count] = player;
            count++;
            System.out.println(player.getName() + " joined Private Club");
        }
    }
}