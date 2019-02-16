package enums;

public enum ClientProfileType {
    ADMIN("ADMIN"),
    GRAND_USER("GRAND_USER"),
    USER("USER");

    String type;

    ClientProfileType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }


    @Override
    public String toString(){
        return this.type;
    }

    public String getName(){
        return this.name();
    }
}
