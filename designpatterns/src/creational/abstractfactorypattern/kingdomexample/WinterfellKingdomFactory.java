package creational.abstractfactorypattern.kingdomexample;

public class WinterfellKingdomFactory extends AbstractKingdomFactory {
    @Override
    King createKing() {
        return new WinterFellKing();
    }

    @Override
    Soilder createSoilder() {
        return new WinterFellSoilder();
    }

    @Override
    Castle createCastle() {
        return new WinterFellCastle();
    }
}