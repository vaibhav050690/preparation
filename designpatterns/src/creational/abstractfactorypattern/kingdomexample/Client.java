package creational.abstractfactorypattern.kingdomexample;

public class Client {
    public static void main(String[] args) {
        AbstractKingdomFactory factory = FactoryMaker.getFactory(KINGDOM.DOTHRAKI);
        createKingdom(factory);
        factory = FactoryMaker.getFactory(KINGDOM.KINGSLANDING);
        createKingdom(factory);
        factory = FactoryMaker.getFactory(KINGDOM.WINTERFELL);
        createKingdom(factory);

    }

    public static void createKingdom(AbstractKingdomFactory factory){
        King king = factory.createKing();
        Castle castle = factory.createCastle();
        Soilder soilder = factory.createSoilder();
        System.out.println(king.getDescription());
        System.out.println(castle.getDescription());
        System.out.println(soilder.getDescription());
    }
}