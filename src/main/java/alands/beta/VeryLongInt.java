package alands.beta;

public class VeryLongInt implements Operations<VeryLongInt>{

    private byte[] digits = null;

    public VeryLongInt(){
        this(new byte[]{0});
    }

    public VeryLongInt(byte[] digits){
        this.digits = digits;
    }

    @Override
    public VeryLongInt add(VeryLongInt v1, VeryLongInt v2) {
        return null;
    }

    public static VeryLongInt stringToVLI(CharSequence input){
        return new VeryLongInt();
    }
}
