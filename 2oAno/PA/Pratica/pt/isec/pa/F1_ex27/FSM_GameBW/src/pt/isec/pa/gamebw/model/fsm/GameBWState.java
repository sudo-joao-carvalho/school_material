package pt.isec.pa.gamebw.model.fsm;

import pt.isec.pa.gamebw.model.data.GameBWData;

public enum GameBWState {
    STATE_1, STATE_2, STATE_3, STATE_N ; // TODO

    public IGameBWState createState(GameBWContext context, GameBWData data) {
        return switch (this) {

            default -> null;
        };
    }
}
