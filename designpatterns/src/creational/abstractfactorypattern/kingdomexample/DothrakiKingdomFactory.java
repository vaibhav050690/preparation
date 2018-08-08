package creational.abstractfactorypattern.kingdomexample;

public class DothrakiKingdomFactory extends AbstractKingdomFactory {

    @Override
    King createKing() {
        return new DothrakiKing();
    }

    @Override
    Soilder createSoilder() {
        return new DothrakiSoilder();
    }

    @Override
    Castle createCastle() {
        return new DothrakiCastle();
    }
}