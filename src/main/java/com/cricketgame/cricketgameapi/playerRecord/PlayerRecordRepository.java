package com.cricketgame.cricketgameapi.playerRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRecordRepository extends JpaRepository<PlayerRecord, UniqueId> {
}
