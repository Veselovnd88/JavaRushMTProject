package restaraunt.kitchen;

public enum Dish {
    FISH,
    STEAK,
    SOUP,
    JUICE,
    WATER;

    public static  String allDishesToString(){
        StringBuilder sb = new StringBuilder();
        Dish[] arr = Dish.values();
        for(int i=0; i<arr.length;i++){
            sb.append(arr[i].toString());
            if(i!=arr.length-1){
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
