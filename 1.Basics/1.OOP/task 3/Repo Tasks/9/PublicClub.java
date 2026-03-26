public class PublicClub extends Club implements MembershipRules {

    public PublicClub(String name) {
        super(name);
    }

    @Override
    public boolean canJoin(Player player) {
        return player.getAge() >= 10;
    }

    @Override
    public void addPlayer(Player player) {
        if (canJoin(player)) {
            players[count] = player;
            count++;
            System.out.println(player.getName() + " joined Public Club");
        } else {
            System.out.println("Rejected from Public Club");
        }
    }
}