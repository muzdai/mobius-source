/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lineage2.gameserver.network.serverpackets.components;

import java.util.NoSuchElementException;

import lineage2.gameserver.model.Player;
import lineage2.gameserver.network.serverpackets.L2GameServerPacket;
import lineage2.gameserver.network.serverpackets.SystemMessage2;

public enum SystemMsg implements IStaticPacket
{
	UNABLE_TO_DISSOLVE_YOUR_CLAN_HAS_REQUESTED_TO_PARTICIPATE_IN_A_CASTLE_SIEGE(13),
	YOU_ARE_NOT_IN_SIEGE(16),
	YOUR_TARGET_IS_OUT_OF_RANGE(22),
	WELCOME_TO_THE_WORLD_OF_LINEAGE_II(34),
	YOU_CAREFULLY_NOCK_AN_ARROW(41),
	YOU_HAVE_EQUIPPED_YOUR_S1(49),
	YOU_CANNOT_USE_THIS_ON_YOURSELF(51),
	YOU_HAVE_EARNED_S1_ADENA(52),
	YOU_HAVE_EARNED_S2_S1S(53),
	YOU_HAVE_EARNED_S1(54),
	NOTHING_HAPPENED(61),
	YOU_MAY_NOT_ATTACK_IN_A_PEACEFUL_ZONE(84),
	YOU_MAY_NOT_ATTACK_THIS_TARGET_IN_A_PEACEFUL_ZONE(85),
	THIS_ITEM_CANNOT_BE_TRADED_OR_SOLD(99),
	YOU_CANNOT_EXIT_THE_GAME_WHILE_IN_COMBAT(101),
	YOU_CANNOT_RESTART_WHILE_IN_COMBAT(102),
	C1_HAS_BEEN_INVITED_TO_THE_PARTY(105),
	INVALID_TARGET(109),
	YOUR_SHIELD_DEFENSE_HAS_SUCCEEDED(111),
	S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS(113),
	C1_HAS_DENIED_YOUR_REQUEST_TO_TRADE(119),
	YOU_BEGIN_TRADING_WITH_C1(120),
	YOU_MAY_NO_LONGER_ADJUST_ITEMS_IN_THE_TRADE_BECAUSE_THE_TRADE_HAS_BEEN_CONFIRMED(122),
	THAT_IS_AN_INCORRECT_TARGET(144),
	THAT_PLAYER_IS_NOT_ONLINE(145),
	C1_IS_ON_ANOTHER_TASK(153),
	ONLY_THE_LEADER_CAN_GIVE_OUT_INVITATIONS(154),
	THE_PARTY_IS_FULL(155),
	YOUR_ATTACK_HAS_FAILED(158),
	C1_IS_A_MEMBER_OF_ANOTHER_PARTY_AND_CANNOT_BE_INVITED(160),
	CONGRATULATIONS__YOUVE_COMPLETED_YOUR_THIRDCLASS_TRANSFER_QUEST(1606),
	WAITING_FOR_ANOTHER_REPLY(164),
	YOU_CANNOT_ADD_YOURSELF_TO_YOUR_OWN_FRIEND_LIST(165),
	FRIEND_LIST_IS_NOT_READY_YET(166),
	C1_IS_ALREADY_ON_YOUR_FRIEND_LIST(167),
	C1_HAS_SENT_A_FRIEND_REQUEST(168),
	ACCEPT_FRIENDSHIP_01_1_TO_ACCEPT_0_TO_DENY(169),
	THE_USER_WHO_REQUESTED_TO_BECOME_FRIENDS_IS_NOT_FOUND_IN_THE_GAME(170),
	C1_IS_NOT_ON_YOUR_FRIEND_LIST(171),
	YOU_LACK_THE_FUNDS_NEEDED_TO_PAY_FOR_THIS_TRANSACTION(172),
	THAT_PERSON_IS_IN_MESSAGE_REFUSAL_MODE(176),
	MESSAGE_REFUSAL_MODE(177),
	CANNOT_SEE_TARGET(181),
	ENTERED_THE_CLAN(195),
	S1_DECLINED_YOUR_CLAN_INVITATION(196),
	YOU_HAVE_RECENTLY_BEEN_DISMISSED_FROM_A_CLAN(199),
	INCORRECT_NAME(204),
	SOME_LINEAGE_II_FEATURES_HAVE_BEEN_LIMITED_FOR_FREE_TRIALS_____(2046),
	THE_RECIPE_IS_INCORRECT(852),
	YOU_DO_NOT_HAVE_ENOUGH_MATERIALS_TO_PERFORM_THAT_ACTION(341),
	PLEASE_REGISTER_A_RECIPE(859),
	NOT_ENOUGH_MP(24),
	YOU_CANNOT_DO_THAT_WHILE_FISHING_(1470),
	S1_HAS_JOINED_THE_CLAN(222),
	S1_HAS_WITHDRAWN_FROM_THE_CLAN(223),
	AFTER_LEAVING_OR_HAVING_BEEN_DISMISSED_FROM_A_CLAN_YOU_MUST_WAIT_AT_LEAST_A_DAY_BEFORE_JOINING_ANOTHER_CLAN(232),
	THE_TARGET_MUST_BE_A_CLAN_MEMBER(234),
	ONLY_THE_CLAN_LEADER_IS_ENABLED(236),
	A_CLAN_LEADER_CANNOT_WITHDRAW_FROM_THEIR_OWN_CLAN(239),
	CLAN_NAME_IS_INVALID(261),
	YOU_DO_NOT_HAVE_THE_NECESSARY_MATERIALS_OR_PREREQUISITES_TO_LEARN_THIS_SKILL(276),
	YOU_HAVE_EARNED_S1_SKILL(277),
	YOU_DO_NOT_HAVE_ENOUGH_ADENA(279),
	YOU_DO_NOT_HAVE_ENOUGH_SP_TO_LEARN_THIS_SKILL(278),
	CLAN_S1_HAS_SUCCESSFULLY_ENGRAVED_THE_HOLY_ARTIFACT(285),
	YOUR_BASE_IS_BEING_ATTACKED(286),
	THE_OPPOSING_CLAN_HAS_STARTED_TO_ENGRAVE_THE_HOLY_ARTIFACT(287),
	THE_CASTLE_GATE_HAS_BEEN_DESTROYED(288),
	AN_OUTPOST_OR_HEADQUARTERS_CANNOT_BE_BUILT_BECAUSE_ONE_ALREADY_EXISTS(289),
	YOU_CANNOT_SET_UP_A_BASE_HERE(290),
	CLAN_S1_IS_VICTORIOUS_OVER_S2S_CASTLE_SIEGE(291),
	S1_HAS_ANNOUNCED_THE_NEXT_CASTLE_SIEGE_TIME(292),
	THE_REGISTRATION_TERM_FOR_S1_HAS_ENDED(293),
	YOU_CANNOT_SUMMON_THE_ENCAMPMENT_BECAUSE_YOU_ARE_NOT_A_MEMBER_OF_THE_SIEGE_CLAN_INVOLVED_IN_THE_CASTLE__FORTRESS__HIDEOUT_SIEGE(294),
	S1S_SIEGE_WAS_CANCELED_BECAUSE_THERE_WERE_NO_CLANS_THAT_PARTICIPATED(295),
	YOU_HAVE_DROPPED_S1(298),
	C1_HAS_OBTAINED_S3_S2(299),
	C1_HAS_OBTAINED_S2(300),
	S2_S1_HAS_DISAPPEARED(301),
	S1_HAS_DISAPPEARED(302),
	CLAN_MEMBER_S1_HAS_LOGGED_INTO_GAME(304),
	THE_PLAYER_DECLINED_TO_JOIN_YOUR_PARTY(305),
	INCORRECT_ITEM_COUNT(351),
	INAPPROPRIATE_ENCHANT_CONDITIONS(355),
	S1_HOURS_UNTIL_CASTLE_SIEGE_CONCLUSION(358),
	S1_MINUTES_UNTIL_CASTLE_SIEGE_CONCLUSION(359),
	THIS_CASTLE_SIEGE_WILL_END_IN_S1_SECONDS(360),
	YOU_HAVE_OBTAINED_A_S1_S2(369),
	C1_HAS_OBTAINED_S2S3(376),
	S1_S2_DISAPPEARED(377),
	C1_PURCHASED_S2(378),
	C1_PURCHASED_S2S3(379),
	C1_PURCHASED_S3_S2S(380),
	SYSTEM_ERROR(399),
	YOU_DO_NOT_POSSESS_THE_CORRECT_TICKET_TO_BOARD_THE_BOAT(402),
	DOES_NOT_FIT_STRENGTHENING_CONDITIONS_OF_THE_SCROLL(424),
	YOU_CANNOT_SUMMON_DURING_A_TRADE_OR_WHILE_USING_A_PRIVATE_STORE(577),
	A_PET_CANNOT_BE_UNSUMMONED_DURING_BATTLE(579),
	DEAD_PETS_CANNOT_BE_RETURNED_TO_THEIR_SUMMONING_ITEM(589),
	YOU_MAY_NOT_RESTORE_A_HUNGRY_PET(594),
	YOU_MAY_NOT_EQUIP_A_PET_ITEM(600),
	YOU_CAN_ONLY_ENTER_UP_128_NAMES_IN_YOUR_FRIENDS_LIST(605),
	THE_FRIENDS_LIST_OF_THE_PERSON_YOU_ARE_TRYING_TO_ADD_IS_FULL_SO_REGISTRATION_IS_NOT_POSSIBLE(606),
	YOU_DO_NOT_HAVE_ANY_FURTHER_SKILLS_TO_LEARN__COME_BACK_WHEN_YOU_HAVE_REACHED_LEVEL_S1(607),
	YOU_HAVE_ALREADY_REQUESTED_A_CASTLE_SIEGE(638),
	YOU_ARE_ALREADY_REGISTERED_TO_THE_ATTACKER_SIDE_AND_MUST_CANCEL_YOUR_REGISTRATION_BEFORE_SUBMITTING_YOUR_REQUEST(642),
	YOU_HAVE_ALREADY_REGISTERED_TO_THE_DEFENDER_SIDE_AND_MUST_CANCEL_YOUR_REGISTRATION_BEFORE_SUBMITTING_YOUR_REQUEST(643),
	YOU_ARE_NOT_YET_REGISTERED_FOR_THE_CASTLE_SIEGE(644),
	ONLY_CLANS_OF_LEVEL_5_OR_HIGHER_MAY_REGISTER_FOR_A_CASTLE_SIEGE(645),
	YOU_DO_NOT_HAVE_THE_AUTHORITY_TO_MODIFY_THE_CASTLE_DEFENDER_LIST(646),
	YOU_DO_NOT_HAVE_THE_AUTHORITY_TO_MODIFY_THE_SIEGE_TIME(647),
	NO_MORE_REGISTRATIONS_MAY_BE_ACCEPTED_FOR_THE_ATTACKER_SIDE(648),
	NO_MORE_REGISTRATIONS_MAY_BE_ACCEPTED_FOR_THE_DEFENDER_SIDE(649),
	YOU_DO_NOT_HAVE_THE_AUTHORITY_TO_POSITION_MERCENARIES(653),
	YOU_DO_NOT_HAVE_THE_AUTHORITY_TO_CANCEL_MERCENARY_POSITIONING(654),
	MERCENARIES_CANNOT_BE_POSITIONED_HERE(655),
	THIS_MERCENARY_CANNOT_BE_POSITIONED_ANYMORE(656),
	POSITIONING_CANNOT_BE_DONE_HERE_BECAUSE_THE_DISTANCE_BETWEEN_MERCENARIES_IS_TOO_SHORT(657),
	THIS_IS_NOT_A_MERCENARY_OF_A_CASTLE_THAT_YOU_OWN_AND_SO_YOU_CANNOT_CANCEL_ITS_POSITIONING(658),
	THIS_IS_NOT_THE_TIME_FOR_SIEGE_REGISTRATION_AND_SO_REGISTRATIONS_CANNOT_BE_ACCEPTED_OR_REJECTED(659),
	THIS_IS_NOT_THE_TIME_FOR_SIEGE_REGISTRATION_AND_SO_REGISTRATION_AND_CANCELLATION_CANNOT_BE_DONE(660),
	S1_ADENA_DISAPPEARED(672),
	ONLY_A_CLAN_LEADER_WHOSE_CLAN_IS_OF_LEVEL_2_OR_HIGHER_IS_ALLOWED_TO_PARTICIPATE_IN_A_CLAN_HALL_AUCTION(673),
	IT_HAS_NOT_YET_BEEN_SEVEN_DAYS_SINCE_CANCELING_AN_AUCTION(674),
	THERE_ARE_NO_CLAN_HALLS_UP_FOR_AUCTION(675),
	SINCE_YOU_HAVE_ALREADY_SUBMITTED_A_BID_YOU_ARE_NOT_ALLOWED_TO_PARTICIPATE_IN_ANOTHER_AUCTION_AT_THIS_TIME(676),
	YOUR_BID_PRICE_MUST_BE_HIGHER_THAN_THE_MINIMUM_PRICE_CURRENTLY_BEING_BID(677),
	YOU_HAVE_CANCELED_YOUR_BID(679),
	YOU_ARE_NO_PRIORITY_RIGHTS_ON_A_SWEEPER(683),
	YOU_CANNOT_MOVE_WHILE_FROZEN(687),
	CASTLE_OWNING_CLANS_ARE_AUTOMATICALLY_REGISTERED_ON_THE_DEFENDING_SIDE(688),
	A_CLAN_THAT_OWNS_A_CASTLE_CANNOT_PARTICIPATE_IN_ANOTHER_SIEGE(689),
	YOU_CANNOT_REGISTER_AS_AN_ATTACKER_BECAUSE_YOU_ARE_IN_AN_ALLIANCE_WITH_THE_CASTLE_OWNING_CLAN(690),
	THE_OTHER_PARTY_IS_FROZEN(692),
	NO_PACKAGES_HAVE_ARRIVED(694),
	YOU_DO_NOT_HAVE_ENOUGH_REQUIRED_ITEMS(701),
	IF_A_BASE_CAMP_DOES_NOT_EXIST_RESURRECTION_IS_NOT_POSSIBLE(716),
	THE_GUARDIAN_TOWER_HAS_BEEN_DESTROYED_AND_RESURRECTION_IS_NOT_POSSIBLE(717),
	THE_EFFECT_OF_S1_HAS_BEEN_REMOVED(749),
	THERE_ARE_NO_OTHER_SKILLS_TO_LEARN(750),
	YOU_CANNOT_POSITION_MERCENARIES_HERE(753),
	THE_CLAN_HALL_WHICH_WAS_PUT_UP_FOR_AUCTION_HAS_BEEN_AWARDED_TO_S1_CLAN(776),
	THE_CLAN_HALL_WHICH_HAD_BEEN_PUT_UP_FOR_AUCTION_WAS_NOT_SOLD_AND_THEREFORE_HAS_BEEN_RELISTED(777),
	OBSERVATION_IS_ONLY_POSSIBLE_DURING_A_SIEGE(780),
	THE_TRYOUTS_ARE_FINISHED(787),
	THE_FINALS_ARE_FINISHED(788),
	THE_TRYOUTS_HAVE_BEGUN(789),
	THE_FINALS_HAVE_BEGUN(790),
	THE_FINAL_MATCH_IS_ABOUT_TO_BEGIN(791),
	YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT(794),
	YOU_ARE_TOO_LATE_THE_REGISTRATION_PERIOD_IS_OVER(800),
	THE_TRYOUTS_ARE_ABOUT_TO_BEGIN(815),
	THE_SIEGE_OF_S1_IS_FINISHED(843),
	THE_SIEGE_TO_CONQUER_S1_HAS_BEGUN(844),
	THE_DEADLINE_TO_REGISTER_FOR_THE_SIEGE_OF_S1_HAS_PASSED(845),
	THE_SIEGE_OF_S1_HAS_BEEN_CANCELED_DUE_TO_LACK_OF_INTEREST(846),
	A_CLAN_THAT_OWNS_A_CLAN_HALL_MAY_NOT_PARTICIPATE_IN_A_CLAN_HALL_SIEGE(847),
	S1_CLAN_HAS_DEFEATED_S2(855),
	THE_SIEGE_OF_S1_HAS_ENDED_IN_A_DRAW(856),
	THE_SEED_HAS_BEEN_SOWN(871),
	THIS_SEED_MAY_NOT_BE_SOWN_HERE(872),
	THE_SYMBOL_HAS_BEEN_ADDED(877),
	THE_PRELIMINARY_MATCH_OF_S1_HAS_ENDED_IN_A_DRAW(858),
	THE_SYMBOL_HAS_BEEN_DELETED(878),
	THE_MANOR_SYSTEM_IS_CURRENTLY_UNDER_MAINTENANCE(879),
	THE_TRANSACTION_IS_COMPLETE(880),
	THE_SYMBOL_CANNOT_BE_DRAWN(899),
	NO_SLOT_EXISTS_TO_DRAW_THE_SYMBOL(900),
	THE_SOUL_CRYSTAL_SUCCEEDED_IN_ABSORBING_A_SOUL(974),
	THE_SOUL_CRYSTAL_WAS_NOT_ABLE_TO_ABSORB_THE_SOUL(975),
	THE_SOUL_CRYSTAL_BROKE_BECAUSE_IT_WAS_NOT_ABLE_TO_ENDURE_THE_SOUL_ENERGY(976),
	THE_SOUL_CRYSTAL_CAUSED_RESONATION_AND_FAILED_AT_ABSORBING_A_SOUL(977),
	THE_SOUL_CRYSTAL_IS_REFUSING_TO_ABSORB_THE_SOUL(978),
	YOU_HAVE_REGISTERED_FOR_A_CLAN_HALL_AUCTION(1004),
	THERE_IS_NOT_ENOUGH_ADENA_IN_THE_CLAN_HALL_WAREHOUSE(1005),
	YOUR_BID_HAS_BEEN_SUCCESSFULLY_PLACED(1006),
	A_HUNGRY_STRIDER_CANNOT_BE_MOUNTED_OR_DISMOUNTED(1008),
	A_STRIDER_CANNOT_BE_RIDDEN_WHEN_DEAD(1009),
	A_DEAD_STRIDER_CANNOT_BE_RIDDEN(1010),
	A_STRIDER_IN_BATTLE_CANNOT_BE_RIDDEN(1011),
	A_STRIDER_CANNOT_BE_RIDDEN_WHILE_IN_BATTLE(1012),
	A_STRIDER_CAN_BE_RIDDEN_ONLY_WHEN_STANDING(1013),
	PETS_CRITICAL_HIT(1017),
	SUMMONED_MONSTERS_CRITICAL_HIT(1028),
	YOU_HAVE_EXCEEDED_THE_QUANTITY_THAT_CAN_BE_INPUTTED(1036),
	PAYMENT_FOR_YOUR_CLAN_HALL_HAS_NOT_BEEN_MADE_PLEASE_ME_PAYMENT_TO_YOUR_CLAN_WAREHOUSE_BY_S1_TOMORROW(1051),
	THE_CLAN_HALL_FEE_IS_ONE_WEEK_OVERDUE_THEREFORE_THE_CLAN_HALL_OWNERSHIP_HAS_BEEN_REVOKED(1052),
	IT_IS_NOT_POSSIBLE_TO_RESURRECT_IN_BATTLEFIELDS_WHERE_A_SIEGE_WAR_IS_TAKING_PLACE(1053),
	WHILE_OPERATING_A_PRIVATE_STORE_OR_WORKSHOP_YOU_CANNOT_DISCARD_DESTROY_OR_TRADE_AN_ITEM(1065),
	S1_HP_HAS_BEEN_RESTORED(1066),
	S2_HP_HAS_BEEN_RESTORED_BY_C1(1067),
	S1_MP_HAS_BEEN_RESTORED(1068),
	S2_MP_HAS_BEEN_RESTORED_BY_C1(1069),
	THE_BID_AMOUNT_MUST_BE_HIGHER_THAN_THE_PREVIOUS_BID(1075),
	A_CLAN_MEMBER_MAY_NOT_BE_DISMISSED_DURING_COMBAT(1117),
	WOULD_YOU_LIKE_TO_OPEN_THE_GATE(1140),
	WOULD_YOU_LIKE_TO_CLOSE_THE_GATE(1141),
	SINCE_S1_ALREADY_EXISTS_NEARBY_YOU_CANNOT_SUMMON_IT_AGAIN(1142),
	THE_TEMPORARY_ALLIANCE_OF_THE_CASTLE_ATTACKER_TEAM_IS_IN_EFFECT(1189),
	THE_TEMPORARY_ALLIANCE_OF_THE_CASTLE_ATTACKER_TEAM_HAS_BEEN_DISSOLVED(1190),
	A_MERCENARY_CAN_BE_ASSIGNED_TO_A_POSITION_FROM_THE_BEGINNING_OF_THE_SEAL_VALIDATION_PERIOD_UNTIL_THE_TIME_WHEN_A_SIEGE_STARTS(1194),
	THIS_MERCENARY_CANNOT_BE_ASSIGNED_TO_A_POSITION_BY_USING_THE_SEAL_OF_STRIFE(1195),
	C1_DIED_AND_DROPPED_S3_S2(1208),
	C1_HAS_DIED_AND_DROPPED_S2_ADENA(1246),
	THE_NEW_SUBCLASS_HAS_BEEN_ADDED(1269),
	YOU_HAVE_SUCCESSFULLY_SWITCHED_S1_TO_S2(1270),
	YOUR_EXCELLENT_SHIELD_DEFENSE_WAS_A_SUCCESS(1281),
	SUBCLASSES_MAY_NOT_BE_CREATED_OR_CHANGED_WHILE_A_SKILL_IS_IN_USE(1295),
	YOU_HAVE_EXITED_THE_PARTY_ROOM(1391),
	C1_HAS_LEFT_THE_PARTY_ROOM(1392),
	YOU_HAVE_BEEN_OUSTED_FROM_THE_PARTY_ROOM(1393),
	C1_HAS_BEEN_KICKED_FROM_THE_PARTY_ROOM(1394),
	THE_PARTY_ROOM_HAS_BEEN_DISBANDED(1395),
	THE_LIST_OF_PARTY_ROOMS_CAN_ONLY_BE_VIEWED_BY_A_PERSON_WHO_IS_NOT_PART_OF_A_PARTY(1396),
	S1_CP_HAS_BEEN_RESTORED(1405),
	S2_CP_HAS_BEEN_RESTORED_BY_C1(1406),
	YOU_DO_NOT_MEET_THE_REQUIREMENTS_TO_ENTER_THAT_PARTY_ROOM(1413),
	YOU_CANNOT_DO_THAT_WHILE_FISHING_2(1471),
	YOUR_OPPONENT_MADE_HASTE_WITH_THEIR_TAIL_BETWEEN_THEIR_LEGS_THE_MATCH_HAS_BEEN_CANCELLED(1493),
	YOUR_OPPONENT_DOES_NOT_MEET_THE_REQUIREMENTS_TO_DO_BATTLE_THE_MATCH_HAS_BEEN_CANCELLED(1494),
	THE_MATCH_WILL_START_IN_S1_SECONDS(1495),
	THE_MATCH_HAS_STARTED(1496),
	CONGRATULATIONS_C1_YOU_WIN_THE_MATCH(1497),
	THERE_IS_NO_VICTOR_THE_MATCH_ENDS_IN_A_TIE(1498),
	YOU_WILL_BE_MOVED_BACK_TO_TOWN_IN_S1_SECONDS(1499),
	C1_DOES_NOT_MEET_THE_PARTICIPATION_REQUIREMENTS_SUBCLASS_CHARACTER_CANNOT_PARTICIPATE_IN_THE_OLYMPIAD(1500),
	C1_DOES_NOT_MEET_THE_PARTICIPATION_REQUIREMENTS_ONLY_NOBLESSE_CHARACTERS_CAN_PARTICIPATE_IN_THE_OLYMPIAD(1501),
	C1_IS_ALREADY_REGISTERED_ON_THE_MATCH_WAITING_LIST(1502),
	YOU_HAVE_BEEN_REGISTERED_FOR_THE_GRAND_OLYMPIAD_WAITING_LIST_FOR_A_CLASS_SPECIFIC_MATCH(1503),
	YOU_ARE_CURRENTLY_REGISTERED_FOR_A_1V1_CLASS_IRRELEVANT_MATCH(1504),
	YOU_HAVE_BEEN_REMOVED_FROM_THE_GRAND_OLYMPIAD_WAITING_LIST(1505),
	YOU_ARE_NOT_CURRENTLY_REGISTERED_FOR_THE_GRAND_OLYMPIAD(1506),
	YOU_CANNOT_EQUIP_THAT_ITEM_IN_A_GRAND_OLYMPIAD_MATCH(1507),
	YOU_CANNOT_USE_THAT_ITEM_IN_A_GRAND_OLYMPIAD_MATCH(1508),
	YOU_CANNOT_USE_THAT_SKILL_IN_A_GRAND_OLYMPIAD_MATCH(1509),
	C1_IS_MAKING_AN_ATTEMPT_TO_RESURRECT_YOU_IF_YOU_CHOOSE_THIS_PATH_S2_EXPERIENCE_WILL_BE_RETURNED_FOR_YOU(1510),
	THE_TARGET_IS_UNAVAILABLE_FOR_SEEDING(1516),
	THE_BLESSED_ENCHANT_FAILED(1517),
	YOU_SHOULD_RELEASE_YOUR_PET_OR_SERVITOR_SO_THAT_IT_DOES_NOT_FALL_OFF_OF_THE_BOAT_AND_DROWN(1523),
	WELCOME_TO_RUNE_HARBOR(1620),
	THE_GRAND_OLYMPIAD_GAMES_ARE_NOT_CURRENTLY_IN_PROGRESS(1651),
	C1_HAS_EARNED_S2_POINTS_IN_THE_GRAND_OLYMPIAD_GAMES(1657),
	C1_HAS_LOST_S2_POINTS_IN_THE_GRAND_OLYMPIAD_GAMES(1658),
	LETHAL_STRIKE(1667),
	YOUR_LETHAL_STRIKE_WAS_SUCCESSFUL(1668),
	THERE_WAS_NOTHING_FOUND_INSIDE(1669),
	FOR_THE_CURRENT_GRAND_OLYMPIAD_YOU_HAVE_PARTICIPATED_IN_S1_MATCHES_S2_WINS_S3_DEFEATS_YOU_CURRENTLY_HAVE_S4_OLYMPIAD_POINTS(1673),
	THIS_COMMAND_CAN_ONLY_BE_USED_BY_A_NOBLESSE(1674),
	A_MANOR_CANNOT_BE_SET_UP_BETWEEN_430_AM_AND_8_PM(1675),
	THIS_AREA_CANNOT_BE_ENTERED_WHILE_MOUNTED_ATOP_OF_A_WYVERN(1687),
	YOU_CANNOT_ENCHANT_WHILE_OPERATING_A_PRIVATE_STORE_OR_PRIVATE_WORKSHOP(1688),
	C1_IS_ALREADY_REGISTERED_ON_THE_CLASS_MATCH_WAITING_LIST(1689),
	C1_IS_ALREADY_REGISTERED_ON_THE_WAITING_LIST_FOR_THE_CLASS_IRRELEVANT_INDIVIDUAL_MATCH(1690),
	YOU_MAY_NOT_OBSERVE_A_GRAND_OLYMPIAD_GAMES_MATCH_WHILE_YOU_ARE_ON_THE_WAITING_LIST(1693),
	ONLY_A_CLAN_LEADER_THAT_IS_A_NOBLESSE_CAN_VIEW_THE_SIEGE_WAR_STATUS_WINDOW_DURING_A_SIEGE_WAR(1694),
	YOUR_APPRENTICE_C1_HAS_LOGGED_IN(1756),
	YOUR_APPRENTICE_C1_HAS_LOGGED_OUT(1757),
	YOUR_SPONSOR_C1_HAS_LOGGED_IN(1758),
	YOUR_SPONSOR_C1_HAS_LOGGED_OUT(1759),
	SINCE_YOUR_CLAN_EMERGED_VICTORIOUS_FROM_THE_SIEGE_S1_POINTS_HAVE_BEEN_ADDED_TO_YOUR_CLANS_REPUTATION_SCORE(1773),
	YOUR_CLAN_HAS_FAILED_TO_DEFEND_THE_CASTLE_S1_POINTS_HAVE_BEEN_DEDUCTED_FROM_YOU_CLAN_REPUTATION_SCORE_AND_ADDED_TO_YOUR_OPPONENTS(1784),
	THE_CLAN_SKILL_S1_HAS_BEEN_ADDED(1788),
	THE_REGISTRATION_PERIOD_FOR_A_CLAN_HALL_WAR_HAS_ENDED(1823),
	YOU_HAVE_BEEN_REGISTERED_FOR_A_CLAN_HALL_WAR(1824),
	YOU_HAVE_FAILED_IN_YOUR_ATTEMPT_TO_REGISTER_FOR_THE_CLAN_HALL_WAR(1825),
	IN_S1_MINUTES_THE_GAME_WILL_BEGIN_ALL_PLAYERS_MUST_HURRY_AND_MOVE_TO_THE_LEFT_SIDE_OF_THE_CLAN_HALLS_ARENA(1826),
	IN_S1_MINUTES_THE_GAME_WILL_BEGIN_ALL_PLAYERS_PLEASE_ENTER_THE_ARENA_NOW(1827),
	IN_S1_SECONDS_THE_GAME_WILL_BEGIN(1828),
	C1_IS_NOT_ALLOWED_TO_USE_THE_PARTY_ROOM_INVITE_COMMAND(1830),
	C1_DOES_NOT_MEET_THE_CONDITIONS_OF_THE_PARTY_ROOM(1831),
	ONLY_A_ROOM_LEADER_MAY_INVITE_OTHERS_TO_A_PARTY_ROOM(1832),
	THE_PARTY_ROOM_IS_FULL(1834),
	THIS_CLAN_HALL_WAR_HAS_BEEN_CANCELLED(1841),
	C1_WISHES_TO_SUMMON_YOU_FROM_S2(1842),
	THE_CLAN_REPUTATION_SCORE_IS_TOO_LOW(1860),
	YOUR_PETSERVITOR_IS_UNRESPONSIVE_AND_WILL_NOT_OBEY_ANY_ORDERS(1864),
	THE_PRELIMINARY_MATCH_WILL_BEGIN_IN_S1_SECONDS(1881),
	THERE_ARE_NO_OFFERINGS_I_OWN_OR_I_MADE_A_BID_FOR(1883),
	YOU_MAY_NOT_USE_ITEMS_IN_A_PRIVATE_STORE_OR_PRIVATE_WORK_SHOP(1891),
	A_SUBCLASS_CANNOT_BE_CREATED_OR_CHANGED_WHILE_YOU_ARE_OVER_YOUR_WEIGHT_LIMIT(1894),
	C1_HAS_ENTERED_THE_PARTY_ROOM(1900),
	S1_HAS_SENT_AN_INVITATION_TO_ROOM_S2(1901),
	INCOMPATIBLE_ITEM_GRADE(1902),
	A_SUBCLASS_MAY_NOT_BE_CREATED_OR_CHANGED_WHILE_A_SERVITOR_OR_PET_IS_SUMMONED(1904),
	YOUR_PET_IS_TOO_HIGH_LEVEL_TO_CONTROL(1918),
	THERE_IS_NO_OPPONENT_TO_RECEIVE_YOUR_CHALLENGE_FOR_A_DUEL(1926),
	C1_HAS_BEEN_CHALLENGED_TO_A_DUEL(1927),
	C1S_PARTY_HAS_BEEN_CHALLENGED_TO_A_DUEL(1928),
	C1_HAS_ACCEPTED_YOUR_CHALLENGE_TO_A_DUEL(1929),
	YOU_HAVE_ACCEPTED_C1S_CHALLENGE_A_DUEL(1930),
	C1_HAS_DECLINED_YOUR_CHALLENGE_TO_A_DUEL(1931),
	C1_HAS_DECLINED_YOUR_CHALLENGE_TO_A_DUEL_(1932),
	YOU_HAVE_ACCEPTED_C1S_CHALLENGE_TO_A_PARTY_DUEL(1933),
	S1_HAS_ACCEPTED_YOUR_CHALLENGE_TO_DUEL_AGAINST_THEIR_PARTY(1934),
	C1_HAS_DECLINED_YOUR_CHALLENGE_TO_A_PARTY_DUEL(1935),
	THE_OPPOSING_PARTY_HAS_DECLINED_YOUR_CHALLENGE_TO_A_DUEL(1936),
	SINCE_THE_PERSON_YOU_CHALLENGED_IS_NOT_CURRENTLY_IN_A_PARTY_THEY_CANNOT_DUEL_AGAINST_YOUR_PARTY(1937),
	C1_HAS_CHALLENGED_YOU_TO_A_DUEL(1938),
	C1S_PARTY_HAS_CHALLENGED_YOUR_PARTY_TO_A_DUEL(1939),
	YOU_ARE_UNABLE_TO_REQUEST_A_DUEL_AT_THIS_TIME(1940),
	IN_A_MOMENT_YOU_WILL_BE_TRANSPORTED_TO_THE_SITE_WHERE_THE_DUEL_WILL_TAKE_PLACE(1944),
	THE_DUEL_WILL_BEGIN_IN_S1_SECONDS(1945),
	LET_THE_DUEL_BEGIN(1949),
	C1_HAS_WON_THE_DUEL(1950),
	C1S_PARTY_HAS_WON_THE_DUEL(1951),
	THE_DUEL_HAS_ENDED_IN_A_TIE(1952),
	ONLY_THE_CLAN_LEADER_MAY_ISSUE_COMMANDS(1966),
	S1(1983),
	THE_FERRY_HAS_ARRIVED_AT_PRIMEVAL_ISLE(1988),
	THE_FERRY_WILL_LEAVE_FOR_RUNE_HARBOR_AFTER_ANCHORING_FOR_THREE_MINUTES(1989),
	THE_FERRY_IS_NOW_DEPARTING_PRIMEVAL_ISLE_FOR_RUNE_HARBOR(1990),
	THE_FERRY_WILL_LEAVE_FOR_PRIMEVAL_ISLE_AFTER_ANCHORING_FOR_THREE_MINUTES(1991),
	THE_FERRY_IS_NOW_DEPARTING_RUNE_HARBOR_FOR_PRIMEVAL_ISLE(1992),
	THE_FERRY_FROM_PRIMEVAL_ISLE_TO_RUNE_HARBOR_HAS_BEEN_DELAYED(1993),
	THE_FERRY_FROM_RUNE_HARBOR_TO_PRIMEVAL_ISLE_HAS_BEEN_DELAYED(1994),
	C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_ENGAGED_IN_A_PRIVATE_STORE_OR_MANUFACTURE(2017),
	C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_FISHING(2018),
	C1_CANNOT_DUEL_BECAUSE_C1S_HP_OR_MP_IS_BELOW_50(2019),
	C1_CANNOT_MAKE_A_CHALLENGE_TO_A_DUEL_BECAUSE_C1_IS_CURRENTLY_IN_A_DUELPROHIBITED_AREA_PEACEFUL_ZONE__SEVEN_SIGNS_ZONE__NEAR_WATER__RESTART_PROHIBITED_AREA(2020),
	C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_ENGAGED_IN_BATTLE(2021),
	C1_CANNOT_DUEL_BECAUSE_C1_IS_ALREADY_ENGAGED_IN_A_DUEL(2022),
	C1_CANNOT_DUEL_BECAUSE_C1_IS_IN_A_CHAOTIC_STATE(2023),
	C1_CANNOT_DUEL_BECAUSE_C1_IS_PARTICIPATING_IN_THE_OLYMPIAD(2024),
	C1_CANNOT_DUEL_BECAUSE_C1_IS_PARTICIPATING_IN_A_CLAN_HALL_WAR(2025),
	C1_CANNOT_DUEL_BECAUSE_C1_IS_PARTICIPATING_IN_A_SIEGE_WAR(2026),
	C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_RIDING_A_BOAT_STEED_OR_STRIDER(2027),
	C1_CANNOT_RECEIVE_A_DUEL_CHALLENGE_BECAUSE_C1_IS_TOO_FAR_AWAY(2028),
	A_SUBCLASS_CANNOT_BE_CREATED_OR_CHANGED_BECAUSE_YOU_HAVE_EXCEEDED_YOUR_INVENTORY_LIMIT(2033),
	S1_CLAN_IS_TRYING_TO_DISPLAY_A_FLAG(2050),
	THAT_WEAPON_CANNOT_PERFORM_ANY_ATTACKS(2066),
	THAT_WEAPON_CANNOT_USE_ANY_OTHER_SKILL_EXCEPT_THE_WEAPONS_SKILL(2067),
	ENEMY_BLOOD_PLEDGES_HAVE_INTRUDED_INTO_THE_FORTRESS(2084),
	SHOUT_AND_TRADE_CHATTING_CANNOT_BE_USED_WHILE_POSSESSING_A_CURSED_WEAPON(2085),
	SEARCH_ON_USER_C2_FOR_THIRDPARTY_PROGRAM_USE_WILL_BE_COMPLETED_IN_S1_MINUTES(2086),
	A_FORTRESS_IS_UNDER_ATTACK(2087),
	S1_MINUTES_UNTIL_THE_FORTRESS_BATTLE_STARTS(2088),
	S1_SECONDS_UNTIL_THE_FORTRESS_BATTLE_STARTS(2089),
	THE_FORTRESS_BATTLE_S1_HAS_BEGUN(2090),
	C1_IS_IN_A_LOCATION_WHICH_CANNOT_BE_ENTERED_THEREFORE_IT_CANNOT_BE_PROCESSED(2096),
	C1S_LEVEL_DOES_NOT_CORRESPOND_TO_THE_REQUIREMENTS_FOR_ENTRY(2097),
	C1S_QUEST_REQUIREMENT_IS_NOT_SUFFICIENT_AND_CANNOT_BE_ENTERED(2098),
	C1S_ITEM_REQUIREMENT_IS_NOT_SUFFICIENT_AND_CANNOT_BE_ENTERED(2099),
	C1_MAY_NOT_REENTER_YET(2100),
	YOU_ARE_NOT_CURRENTLY_IN_A_PARTY_SO_YOU_CANNOT_ENTER(2101),
	YOU_CANNOT_ENTER_DUE_TO_THE_PARTY_HAVING_EXCEEDED_THE_LIMIT(2102),
	YOU_CANNOT_ENTER_BECAUSE_YOU_ARE_NOT_ASSOCIATED_WITH_THE_CURRENT_COMMAND_CHANNEL(2103),
	THE_MAXIMUM_NUMBER_OF_INSTANCE_ZONES_HAS_BEEN_EXCEEDED(2104),
	YOU_HAVE_ENTERED_ANOTHER_INSTANCE_ZONE_THEREFORE_YOU_CANNOT_ENTER_CORRESPONDING_DUNGEON(2105),
	THIS_DUNGEON_WILL_EXPIRE_IN_S1_MINUTES(2106),
	YOU_CANNOT_CONVERT_THIS_ITEM(2130),
	CONGRATULATIONS__YOUVE_COMPLETED_A_CLASS_TRANSFER(1308),
	THE_TARGET_IS_NOT_A_FLAGPOLE_SO_A_FLAG_CANNOT_BE_DISPLAYED(2154),
	A_FLAG_IS_ALREADY_BEING_DISPLAYED_ANOTHER_FLAG_CANNOT_BE_DISPLAYED(2155),
	THERE_ARE_NOT_ENOUGH_NECESSARY_ITEMS_TO_USE_THE_SKILL(2156),
	FORCE_ATTACK_IS_IMPOSSIBLE_AGAINST_A_TEMPORARY_ALLIED_MEMBER_DURING_A_SIEGE(2158),
	THE_BARRACKS_HAVE_BEEN_SEIZED(2164),
	THE_BARRACKS_FUNCTION_HAS_BEEN_RESTORED(2165),
	ALL_BARRACKS_ARE_OCCUPIED(2166),
	C1_HAS_ACQUIRED_THE_FLAG(2168),
	YOUR_CLAN_HAS_BEEN_REGISTERED_TO_S1S_FORTRESS_BATTLE(2169),
	C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_POLYMORPHED(2174),
	PARTY_DUEL_CANNOT_BE_INITIATED_DUE_TO_A_POLYMORPHED_PARTY_MEMBER(2175),
	THE_FORTRESS_BATTLE_OF_S1_HAS_FINISHED(2183),
	S1_IS_VICTORIOUS_IN_THE_FORTRESS_BATTLE_OF_S2(2184),
	ONLY_A_PARTY_LEADER_CAN_MAKE_THE_REQUEST_TO_ENTER(2185),
	YOU_CANNOT_BOARD_A_SHIP_WHILE_YOU_ARE_POLYMORPHED(2213),
	THE_BALLISTA_HAS_BEEN_SUCCESSFULLY_DESTROYED(2217),
	THIS_SQUAD_SKILL_HAS_ALREADY_BEEN_ACQUIRED(2219),
	THE_PREVIOUS_LEVEL_SKILL_HAS_NOT_BEEN_LEARNED(2220),
	IT_IS_NOT_POSSIBLE_TO_REGISTER_FOR_THE_CASTLE_SIEGE_SIDE_OR_CASTLE_SIEGE_OF_A_HIGHER_CASTLE_IN_THE_CONTRACT(2227),
	INSTANCE_ZONE_TIME_LIMIT(2228),
	THERE_IS_NO_INSTANCE_ZONE_UNDER_A_TIME_LIMIT(2229),
	S1_WILL_BE_AVAILABLE_FOR_REUSE_AFTER_S2_HOURS_S3_MINUTES(2230),
	SIEGE_REGISTRATION_IS_NOT_POSSIBLE_DUE_TO_YOUR_CASTLE_CONTRACT(2233),
	YOU_ARE_PARTICIPATING_IN_THE_SIEGE_OF_S1_THIS_SIEGE_IS_SCHEDULED_FOR_2_HOURS(2238),
	S1_MINUTES_REMAINING(2244),
	S1_SECONDS_REMAINING(2245),
	THE_CONTEST_WILL_BEGIN_IN_S1_MINUTES(2246),
	YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_TRANSFORMED(2247),
	YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_PETRIFIED(2248),
	YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_DEAD(2249),
	YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_FISHING(2250),
	YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_IN_BATTLE(2251),
	YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_IN_A_DUEL(2252),
	YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_SITTING(2253),
	YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_CASTING(2254),
	YOU_CANNOT_BOARD_AN_AIRSHIP_WHEN_A_CURSED_WEAPON_IS_EQUIPPED(2255),
	YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_HOLDING_A_FLAG(2256),
	YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_A_PET_OR_A_SERVITOR_IS_SUMMONED(2257),
	YOU_HAVE_ALREADY_BOARDED_ANOTHER_AIRSHIP(2258),
	THIS_SKILL_CANNOT_BE_LEARNED_WHILE_IN_THE_SUBCLASS_STATE(2273),
	YOU_CANNOT_WEAR_S1_BECAUSE_YOU_ARE_NOT_WEARING_A_BRACELET(2286),
	YOU_CANNOT_EQUIP_S1_BECAUSE_YOU_DO_NOT_HAVE_ANY_AVAILABLE_SLOTS(2287),
	AGATHION_SKILLS_CAN_BE_USED_ONLY_WHEN_YOUR_AGATHION_IS_SUMMONED(2292),
	THERE_ARE_S2_SECONDS_REMAINING_IN_S1S_REUSE_TIME(2303),
	THERE_ARE_S2_MINUTES_S3_SECONDS_REMAINING_IN_S1S_REUSE_TIME(2304),
	THERE_ARE_S2_HOURS_S3_MINUTES_AND_S4_SECONDS_REMAINING_IN_S1S_REUSE_TIME(2305),
	YOUR_CHARM_OF_COURAGE_IS_TRYING_TO_RESURRECT_YOU(2306),
	ONLY_CLANS_WHO_ARE_LEVEL_4_OR_ABOVE_CAN_REGISTER_FOR_BATTLE_AT_DEVASTATED_CASTLE_AND_FORTRESS_OF_THE_DEAD(2328),
	S1_SECONDS_TO_GAME_END(2347),
	RESURRECTION_WILL_TAKE_PLACE_IN_THE_WAITING_ROOM_AFTER_S1_SECONDS(2370),
	END_MATCH(2374),
	A_PARTY_CANNOT_BE_FORMED_IN_THIS_AREA(2388),
	YOU_HAVE_USED_THE_FEATHER_OF_BLESSING_TO_RESURRECT(2391),
	THAT_PET_SERVITOR_SKILL_CANNOT_BE_USED_BECAUSE_IT_IS_RECHARGING(2396),
	INSTANT_ZONE_CURRENTLY_IN_USE_S1(2400),
	CLAN_LORD_C2_WHO_LEADS_CLAN_S1_HAS_BEEN_DECLARED_THE_LORD_OF_THE_S3_TERRITORY(2401),
	THE_TERRITORY_WAR_REQUEST_PERIOD_HAS_ENDED(2402),
	THE_TERRITORY_WAR_BEGINS_IN_10_MINUTES(2403),
	THE_TERRITORY_WAR_BEGINS_IN_5_MINUTES(2404),
	THE_TERRITORY_WAR_BEGINS_IN_1_MINUTE(2405),
	YOU_ARE_CURRENTLY_REGISTERED_FOR_A_3_VS_3_CLASS_IRRELEVANT_TEAM_MATCH(2408),
	C1_IS_ALREADY_REGISTERED_ON_THE_WAITING_LIST_FOR_THE_3_VS_3_CLASS_IRRELEVANT_TEAM_MATCH(2440),
	ONLY_A_PARTY_LEADER_CAN_REQUEST_A_TEAM_MATCH(2441),
	THE_REQUEST_CANNOT_BE_MADE_BECAUSE_THE_REQUIREMENTS_HAVE_NOT_BEEN_MET(2442),
	THE_BATTLEFIELD_CHANNEL_HAS_BEEN_ACTIVATED(2445),
	THE_BATTLEFIELD_CHANNEL_HAS_BEEN_DEACTIVATED(2446),
	FIVE_YEARS_HAVE_PASSED_SINCE_THIS_CHARACTERS_CREATION(2447),
	YOUR_BIRTHDAY_GIFT_HAS_ARRIVED(2448),
	THERE_ARE_S1_DAYS_UNTIL_YOUR_CHARACTERS_BIRTHDAY(2449),
	C1S_BIRTHDAY_IS_S3S4S2(2450),
	IN_ORDER_TO_ACQUIRE_AN_AIRSHIP_THE_CLANS_LEVEL_MUST_BE_LEVEL_5_OR_HIGHER(2456),
	AN_AIRSHIP_CANNOT_BE_SUMMONED_BECAUSE_EITHER_YOU_HAVE_NOT_REGISTERED_YOUR_AIRSHIP_LICENSE_OR_THE_AIRSHIP_HAS_NOT_YET_BEEN_SUMMONED(2457),
	YOUR_CLANS_AIRSHIP_IS_ALREADY_BEING_USED_BY_ANOTHER_CLAN_MEMBER(2458),
	THE_AIRSHIP_SUMMON_LICENSE_HAS_ALREADY_BEEN_ACQUIRED(2459),
	THE_CLAN_OWNED_AIRSHIP_ALREADY_EXISTS(2460),
	AN_AIRSHIP_CANNOT_BE_SUMMONED_BECAUSE_YOU_DONT_HAVE_ENOUGH_S1(2462),
	THE_AIRSHIPS_FUEL_EP_WILL_SOON_RUN_OUT(2463),
	THE_AIRSHIPS_FUEL_EP_HAS_RUN_OUT(2464),
	YOUR_SHIP_CANNOT_TELEPORT_BECAUSE_IT_DOES_NOT_HAVE_ENOUGH_FUEL_FOR_THE_TRIP(2491),
	THE_S1_WARD_HAS_BEEN_DESTROYED_C2_NOW_HAS_THE_TERRITORY_WARD(2750),
	THE_CHARACTER_THAT_ACQUIRED_S1S_WARD_HAS_BEEN_KILLED(2751),
	YOU_CANNOT_ENTER_BECAUSE_YOU_DO_NOT_MEET_THE_REQUIREMENTS(2706),
	YOU_CANNOT_REGISTER_WHILE_IN_POSSESSION_OF_A_CURSED_WEAPON(2708),
	APPLICANTS_FOR_THE_OLYMPIAD_UNDERGROUND_COLISEUM_OR_KRATEIS_CUBE_MATCHES_CANNOT_REGISTER(2709),
	YOU_CANNOT_BOARD_BECAUSE_YOU_DO_NOT_MEET_THE_REQUIREMENTS(2727),
	YOU_CANNOT_CONTROL_THE_HELM_WHILE_TRANSFORMED(2729),
	YOU_CANNOT_CONTROL_THE_HELM_WHILE_YOU_ARE_PETRIFIED(2730),
	YOU_CANNOT_CONTROL_THE_HELM_WHEN_YOU_ARE_DEAD(2731),
	YOU_CANNOT_CONTROL_THE_HELM_WHILE_FISHING(2732),
	YOU_CANNOT_CONTROL_THE_HELM_WHILE_IN_A_BATTLE(2733),
	YOU_CANNOT_CONTROL_THE_HELM_WHILE_IN_A_DUEL(2734),
	YOU_CANNOT_CONTROL_THE_HELM_WHILE_IN_A_SITTING_POSITION(2735),
	YOU_CANNOT_CONTROL_THE_HELM_WHILE_USING_A_SKILL(2736),
	YOU_CANNOT_CONTROL_THE_HELM_WHILE_A_CURSED_WEAPON_IS_EQUIPPED(2737),
	YOU_CANNOT_CONTROL_THE_HELM_WHILE_HOLDING_A_FLAG(2738),
	YOU_CANNOT_CONTROL_THE_HELM_BECAUSE_YOU_DO_NOT_MEET_THE_REQUIREMENTS(2739),
	THIS_ACTION_IS_PROHIBITED_WHILE_STEERING(2740),
	THIS_TYPE_OF_ATTACK_IS_PROHIBITED_WHEN_ALLIED_TROOPS_ARE_THE_TARGET(2753),
	YOU_CANNOT_BE_SIMULTANEOUSLY_REGISTERED_FOR_PVP_MATCHES_SUCH_AS_THE_OLYMPIAD_UNDERGROUND_COLISEUM_AERIAL_CLEFT_KRATEIS_CUBE_AND_HANDYS_BLOCK_CHECKERS(2754),
	ANOTHER_PLAYER_IS_PROBABLY_CONTROLLING_THE_TARGET(2756),
	YOU_MUST_TARGET_THE_ONE_YOU_WISH_TO_CONTROL(2761),
	YOU_CANNOT_CONTROL_BECAUSE_YOU_ARE_TOO_FAR(2762),
	THE_EFFECT_OF_TERRITORY_WARD_IS_DISAPPEARING(2776),
	THE_AIRSHIP_SUMMON_LICENSE_HAS_BEEN_ENTERED(2777),
	YOU_CANNOT_TELEPORT_WHILE_IN_POSSESSION_OF_A_WARD(2778),
	MERCENARY_PARTICIPATION_IS_REQUESTED_IN_S1_TERRITORY(2788),
	MERCENARY_PARTICIPATION_REQUEST_IS_CANCELLED_IN_S1_TERRITORY(2789),
	CLAN_PARTICIPATION_IS_REQUESTED_IN_S1_TERRITORY(2790),
	CLAN_PARTICIPATION_REQUEST_IS_CANCELLED_IN_S1_TERRITORY(2791),
	YOU_MUST_HAVE_A_MINIMUM_OF_S1_PEOPLE_TO_ENTER_THIS_INSTANT_ZONE(2793),
	THE_TERRITORY_WAR_CHANNEL_AND_FUNCTIONS_WILL_NOW_BE_DEACTIVATED(2794),
	YOUVE_ALREADY_REQUESTED_A_TERRITORY_WAR_IN_ANOTHER_TERRITORY_ELSEWHERE(2795),
	THE_CLAN_WHO_OWNS_THE_TERRITORY_CANNOT_PARTICIPATE_IN_THE_TERRITORY_WAR_AS_MERCENARIES(2796),
	IT_IS_NOT_A_TERRITORY_WAR_REGISTRATION_PERIOD_SO_A_REQUEST_CANNOT_BE_MADE_AT_THIS_TIME(2797),
	THE_TERRITORY_WAR_WILL_END_IN_S1HOURS(2798),
	THE_TERRITORY_WAR_WILL_END_IN_S1MINUTES(2799),
	S1_SECONDS_TO_THE_END_OF_TERRITORY_WAR(2900),
	YOU_CANNOT_FORCE_ATTACK_A_MEMBER_OF_THE_SAME_TERRITORY(2901),
	YOUVE_ACQUIRED_THE_WARD(2902),
	TERRITORY_WAR_HAS_BEGUN(2903),
	TERRITORY_WAR_HAS_ENDED(2904),
	YOUVE_REQUESTED_C1_TO_BE_ON_YOUR_FRIENDS_LIST(2911),
	CLAN_S1_HAS_SUCCEEDED_IN_CAPTURING_S2S_TERRITORY_WARD(2913),
	THE_TERRITORY_WAR_WILL_BEGIN_IN_20_MINUTES(2914),
	THIS_CLAN_MEMBER_CANNOT_WITHDRAW_OR_BE_EXPELLED_WHILE_PARTICIPATING_IN_A_TERRITORY_WAR(2915),
	ONLY_CHARACTERS_WHO_ARE_LEVEL_40_OR_ABOVE_WHO_HAVE_COMPLETED_THEIR_SECOND_CLASS_TRANSFER_CAN_REGISTER_IN_A_TERRITORY_WAR(2918),
	THE_DISGUISE_SCROLL_CANNOT_BE_USED_BECAUSE_IT_IS_MEANT_FOR_USE_IN_A_DIFFERENT_TERRITORY(2936),
	A_TERRITORY_OWNING_CLAN_MEMBER_CANNOT_USE_A_DISGUISE_SCROLL(2937),
	THE_DISGUISE_SCROLL_CANNOT_BE_USED_WHILE_YOU_ARE_ENGAGED_IN_A_PRIVATE_STORE_OR_MANUFACTURE_WORKSHOP(2938),
	A_DISGUISE_CANNOT_BE_USED_WHEN_YOU_ARE_IN_A_CHAOTIC_STATE(2939),
	THE_TERRITORY_WAR_EXCLUSIVE_DISGUISE_AND_TRANSFORMATION_CAN_BE_USED_20_MINUTES_BEFORE_THE_START_OF_THE_TERRITORY_WAR_TO_10_MINUTES_AFTER_ITS_END(2955),
	A_CHARACTER_BORN_ON_FEBRUARY_29_WILL_RECEIVE_A_GIFT_ON_FEBRUARY_28(2957),
	AN_AGATHION_HAS_ALREADY_BEEN_SUMMONED(2958),
	THE_COMMAND_CHANNEL_MATCHING_ROOM_WAS_CANCELLED(2994),
	YOU_CANNOT_ENTER_THE_COMMAND_CHANNEL_MATCHING_ROOM_BECAUSE_YOU_DO_NOT_MEET_THE_REQUIREMENTS(2996),
	YOU_EXITED_FROM_THE_COMMAND_CHANNEL_MATCHING_ROOM(2997),
	YOU_WERE_EXPELLED_FROM_THE_COMMAND_CHANNEL_MATCHING_ROOM(2998),
	THE_COMMAND_CHANNEL_AFFILIATED_PARTYS_PARTY_MEMBER_CANNOT_USE_THE_MATCHING_SCREEN(2999),
	THE_COMMAND_CHANNEL_MATCHING_ROOM_WAS_CREATED(3000),
	THE_COMMAND_CHANNEL_MATCHING_ROOM_INFORMATION_WAS_EDITED(3001),
	C1_ENTERED_THE_COMMAND_CHANNEL_MATCHING_ROOM(3003),
	A_USER_CURRENTLY_PARTICIPATING_IN_THE_OLYMPIAD_CANNOT_SEND_PARTY_AND_FRIEND_INVITATIONS(3094),
	THE_COUPLE_ACTION_WAS_DENIED(3119),
	THE_REQUEST_CANNOT_BE_COMPLETED_BECAUSE_THE_TARGET_DOES_NOT_MEET_LOCATION_REQUIREMENTS(3120),
	THE_COUPLE_ACTION_WAS_CANCELLED(3121),
	C1_IS_ALREADY_PARTICIPATING_IN_A_COUPLE_ACTION_AND_CANNOT_BE_REQUESTED_FOR_ANOTHER_COUPLE_ACTION(3126),
	YOU_HAVE_REQUESTED_A_COUPLE_ACTION_WITH_C1(3150),
	C1_IS_SET_TO_REFUSE_DUEL_REQUESTS_AND_CANNOT_RECEIVE_A_DUEL_REQUEST(3169),
	S1_WAS_SUCCESSFULLY_ADDED_TO_YOUR_CONTACT_LIST(3214),
	THE_NAME_S1__DOESNT_EXIST(3215),
	THE_NAME_ALREADY_EXISTS_ON_THE_ADDED_LIST(3216),
	THE_NAME_IS_NOT_CURRENTLY_REGISTERED(3217),
	S1_WAS_SUCCESSFULLY_DELETED_FROM_YOUR_CONTACT_LIST(3219),
	YOU_CANNOT_ADD_YOUR_OWN_NAME(3221),
	THE_MAXIMUM_NUMBER_OF_NAMES_100_HAS_BEEN_REACHED(3222),
	THE_MAXIMUM_MATCHES_YOU_CAN_PARTICIPATE_IN_1_WEEK_IS_70(3224),
	THE_TOTAL_NUMBER_OF_MATCHES_THAT_CAN_BE_ENTERED_IN_1_WEEK_IS_60_CLASS_IRRELEVANT_INDIVIDUAL_MATCHES_30_SPECIFIC_MATCHES_AND_10_TEAM_MATCHES(3225),
	MP_BECAME_0_AND_THE_ARCANE_SHIELD_IS_DISAPPEARING(3256),
	YOU_HAVE_ACQUIRED_S1_EXP_BONUS_S2_AND_S3_SP_BONUS_S4(3259),
	YOU_HAVE_S1_MATCHES_REMAINING_THAT_YOU_CAN_PARTICIPATE_IN_THIS_WEEK_S2_1_VS_1_CLASS_MATCHES_S3_1_VS_1_MATCHES__S4_3_VS_3_TEAM_MATCHES(3261),
	THERE_ARE_S2_SECONDS_REMAINING_FOR_S1S_REUSE_TIME(3263),
	THERE_ARE_S2_MINUTES_S3_SECONDS_REMAINING_FOR_S1S_REUSE_TIME(3264),
	THERE_ARE_S2_HOURS_S3_MINUTES_S4_SECONDS_REMAINING_FOR_S1S_REUSE_TIME(3265),
	C1_IS_SET_TO_REFUSE_COUPLE_ACTIONS_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION(3164),
	THE_ANGEL_NEVIT_HAS_BLESSED_YOU_FROM_ABOVE_YOU_ARE_IMBUED_WITH_FULL_VITALITY_AS_WELL_AS_A_VITALITY_REPLENISHING_EFFECT(3266),
	YOU_ARE_STARTING_TO_FEEL_THE_EFFECTS_OF_NEVITS_BLESSING(3267),
	YOU_ARE_FURTHER_INFUSED_WITH_THE_BLESSINGS_OF_NEVIT_CONTINUE_TO_BATTLE_EVIL_WHEREVER_IT_MAY_LURK(3268),
	NEVITS_BLESSING_SHINES_STRONGLY_FROM_ABOVE_YOU_CAN_ALMOST_SEE_HIS_DIVINE_AURA(3269),
	NEVITS_BLESSING_HAS_ENDED_CONTINUE_YOUR_JOURNEY_AND_YOU_WILL_SURELY_MEET_HIS_FAVOR_AGAIN_SOMETIME_SOON(3275),
	YOU_DO_NOT_HAVE_ENOUGH_ADENA_TO_REGISTER_THE_ITEM(3364),
	YOU_CANNOT_CHANGE_CLASS_BECAUSE_OF_IDENTIFY_CRISIS(3574),
	THE_MENTORING_RELATIONSHIP_WITH_S1_HAS_BEEN_CANCELED(3689),
	DO_TOU_WISH_TO_MAKE_S1_YOUR_MENTOR_CLASS_S2_LEVEL_S3(3690),
	FROM_NOW_ON_S1_WILL_BE_YOUR_MENTOR(3691),
	FROM_NOW_ON_S1_WILL_BE_YOUR_MENTEE(3692),
	A_MENTOR_CAN_HAVE_UP_TO_3_MENTEES_AT_THE_SAME_TIME(3693),
	YOU_MUST_AWAKEN_IN_ORDER_TO_BECOME_A_MENTOR(3694),
	YOU_MENTEE_S1_HAS_CONNECTED(3695),
	YOU_MENTOR_S1_HAS_CONNECTED(3696),
	YOU_MENTEE_S1_HAS_DISCONNECTED(3697),
	YOU_MENTOR_S1_HAS_DISCONNECTED(3698),
	S1_HAS_DECLINED_BECOMING_YOUR_MENTEE(3699),
	YOU_CANNOT_BECOME_YOUR_OWN_MENTEE(3701),
	S1_ALREADY_HAS_A_MENTOR(3702),
	S1_IS_ABOVE_LEVEL_86_AND_CANNOT_BECOME_A_MENTEE(3703),
	S1_DOES_NOT_HAVE_THE_ITEM_NEDEED_TO_BECOME_A_MENTEE(3704),
	THE_MENTEE_S1_HAS_REACHED_LEVEL_86(3705),
	YOU_REACHED_LEVEL_86_RELATIONSHIP_WITH_S1_CAME_TO_AN_END(3706),
	YOU_HAVE_OFFERED_TO_BECOME_S1_MENTOR(3707),
	YOU_CAN_BOND_WITH_A_NEW_MENTEE_IN_S1_DAYS_S2_HOUR_S3_MINUTE(3713),
	THE_SKILL_HAS_BEEN_CANCELED_BECAUSE_YOU_HAVE_INSUFFICIENT_ENERGY(6042),
	YOUR_ENERGY_CANNOT_BE_REPLENISHED_BECAUSE_CONDITIONS_ARE_NOT_MET(6043),
	ENERGY_S1_REPLENISHED(6044),
	YOU_CANNOT_BOOKMARK_THIS_LOCATION_BECAUSE_YOU_DO_NOT_HAVE_A_MY_TELEPORT_FLAG(6501),
	YOUR_CLOAK_HAS_BEEN_UNEQUIPPED_BECAUSE_YOUR_ARMOR_SET_IS_NO_LONGER_COMPLETE(2451),
	ITEM_PURCHASE_HAS_FAILED(3371),
	THIS_TERRITORY_CAN_NOT_CHANGE_CLASS(3684),
	YOU_CAN_NOT_CHANGE_CLASS_IN_TRANSFORMATION(3677),
	VITALITY_IS_APPLIED_300(3525),
	YOU_CAN_NOT_CHANGE_THE_ATTRIBUTE_WHILE_OPERATING_A_PRIVATE_STORE_OR_PRIVATE_WORKSHOP(3659),
	THE_ITEM_FOR_CHANGING_AN_ATTRIBUTE_DOES_NOT_EXIST(3669),
	THE_COMMUNITY_SERVER_IS_CURRENTLY_OFFLINE(938),
	CANCELLATION_OF_SALE_FOR_THE_ITEM_IS_SUCCESSFUL(3485),
	STOPPED_SEARCHING_THE_PARTY(3453),
	YOU_ARE_REGISTERED_ON_THE_WAITING_LIST(3452),
	THE_PLAYER_TO_BE_REPLACED_DOES_NOT_EXIST(3454),
	YOUR_NUMBER_OF_MY_TELEPORTS_SLOTS_HAS_REACHED_ITS_MAXIMUM_LIMIT(2390),
	THE_NUMBER_OF_MY_TELEPORTS_SLOTS_HAS_BEEN_INCREASED(2409),
	LOOKING_FOR_A_PLAYER_WHO_WILL_REPLACE_S1(3445);
	private final L2GameServerPacket _message;
	private final int _id;
	private final byte _size;
	
	SystemMsg(int i)
	{
		_id = i;
		if (name().contains("S4") || name().contains("C4"))
		{
			_size = 4;
			_message = null;
		}
		else if (name().contains("S3") || name().contains("C3"))
		{
			_size = 3;
			_message = null;
		}
		else if (name().contains("S2") || name().contains("C2"))
		{
			_size = 2;
			_message = null;
		}
		else if (name().contains("S1") || name().contains("C1"))
		{
			_size = 1;
			_message = null;
		}
		else
		{
			_size = 0;
			_message = new SystemMessage2(this);
		}
	}
	
	public int getId()
	{
		return _id;
	}
	
	public byte size()
	{
		return _size;
	}
	
	public static SystemMsg valueOf(int id)
	{
		for (SystemMsg m : values())
		{
			if (m.getId() == id)
			{
				return m;
			}
		}
		throw new NoSuchElementException("Not find SystemMsg by id: " + id);
	}
	
	@Override
	public L2GameServerPacket packet(Player player)
	{
		if (_message == null)
		{
			throw new NoSuchElementException("Running SystemMsg.packet(Player), but message require arguments: " + name());
		}
		return _message;
	}
}
