package creational.abstractfactorypattern.kingdomexample;

public class KingslandingKingdomFactory extends AbstractKingdomFactory {
    @Override
    King createKing() {
        return new KingsLandingKing();
    }

    @Override
    Soilder createSoilder() {
        return new KingsLandingSoilder();
    }

    @Override
    Castle createCastle() {
        return new KingsLandingCastle();
    }
}