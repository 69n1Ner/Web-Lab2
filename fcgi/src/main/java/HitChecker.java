import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HitChecker {

    public static boolean isHit(int x, double y, double r) throws ValidateException{
        if (x >= 0 && y<= 0){
            return x - y <= r;
        }else if (x <= 0 && y >= 0){
            return x*x + y+y <= (r/2) * (r/2);
        } else if (x <= 0 && y <= 0) {
            return x >= -r && y >= -r/2;
        } else return false;

    }

    public static HashMap<String, Number> validate(String rawX, String rawY, String rawR) throws ValidateException{
        int x ;
        try{
            x = Integer.parseInt(rawX);
        }catch (NumberFormatException ex){
            throw new ValidateException("X is not a number",400); // 400 Bad Request
        }

        double y = getDouble(rawY,"Y");
        double r = getDouble(rawR,"R");

        ArrayList<Integer> validX = new ArrayList<>(List.of(-5,-4,-3,-3,-2,-1,0,1,2,3));
        ArrayList<Double> validR = new ArrayList<>(List.of(1.0,1.5,2.0,2.5,3.0));

        String defError = " is outside the definition";
        if (!validX.contains(x)) throw new ValidateException("X" + defError,422); //422 Unprocessable Entity
        if (y < -5 || y > 3) throw new ValidateException("Y" + defError,422);
        if (!validR.contains(r)) throw new ValidateException("R" + defError,422);

        HashMap<String,Number> map = new HashMap<>();
        map.put("x",x);
        map.put("y",y);
        map.put("r",r);
        return map;
    }

    private static double getDouble(String input,String inputName) throws ValidateException{
        try{
            return Double.parseDouble(input);
        }catch (NumberFormatException ex){
            throw new ValidateException(inputName + " is not an number",400);
        }
    }
}
