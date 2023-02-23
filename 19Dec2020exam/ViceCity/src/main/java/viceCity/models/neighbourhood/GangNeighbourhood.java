package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.Repository;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public class GangNeighbourhood implements Neighbourhood {
    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {
        // PHASE 1
        Repository<Gun> mainPlayerRepo = mainPlayer.getGunRepository();
        // ползваме Queue
        Deque<Gun> mainPlayerGuns = new ArrayDeque<>(mainPlayerRepo.getModels());
        Deque<Player> players = new ArrayDeque<>(civilPlayers);

        Player player = players.poll();
        Gun gun = mainPlayerGuns.poll();
        // while we have players
        // while we have guns

        while (player != null && gun != null) {
            // 1 gun 1 player
            while (gun.canFire() && player.isAlive()) {
                int shot = gun.fire();
                player.takeLifePoints(shot);
            }
            // get a new gun
            // get new target
            if (gun.canFire()) {
                player = players.poll();
            } else {
                gun = mainPlayerGuns.poll();
            }
        }

        // PHASE 2
        for (Player civilPlayer : civilPlayers) {
            if (civilPlayer.isAlive()) {
                Repository<Gun> civilPlayersGunRepo = civilPlayer.getGunRepository();
                Deque<Gun> civilPlayerGuns = new ArrayDeque<>(civilPlayersGunRepo.getModels());
                Gun civilPlayerGun = civilPlayerGuns.poll();
                while (civilPlayerGun != null) {
                    while (civilPlayerGun.canFire() && mainPlayer.isAlive()) {
                        int shot = civilPlayerGun.fire();
                        mainPlayer.takeLifePoints(shot);
                    }

                    if (mainPlayer.isAlive()) {
                        return;
                    }
                    civilPlayerGun = civilPlayerGuns.poll();
                }
            }
        }
    }
}
