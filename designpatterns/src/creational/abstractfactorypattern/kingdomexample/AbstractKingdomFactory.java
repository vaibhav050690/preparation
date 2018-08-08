package creational.abstractfactorypattern.kingdomexample;

public abstract class AbstractKingdomFactory {

    abstract King createKing();
    abstract Soilder createSoilder();
    abstract Castle createCastle();

}