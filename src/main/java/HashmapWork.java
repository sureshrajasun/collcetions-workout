import java.util.HashMap;

/*
    Scenario 1 : No equals and/or hashcode function.
        map still put all the key values and it will allow duplicates keys added in the map.
        The size gives the number of elements added, but we can't get the value using the key
        it will always gives null

    Scenario 2 : With  hashcode method returns 0 or 1 constant value and no equals method
        Map Still put all the keys values even the duplicates are added,
        The Size gives the actual number of elements added.
        but during get value when we use the key we get null on all the keys even for duplicate keys
        and unique key as well.

   Scenario 3 : with proper hashcode method implementation and no Equals method
            Map Still put all the keys values even the duplicates are added,
            The Size gives the actual number of elements added.
            but during get value when we use the key we get null on all the keys even for duplicate keys
            and unique key as well. This is same as Scenario 2 and Scenario 1

   Scenario 4 : with proper hashcode method implementation and  Equals method returns false always
            Map Still put all the keys values even the duplicates are added,
            The Size gives the actual number of elements added.
            but during get value when we use the key we get null on all the keys even for duplicate keys
            and unique key as well. This is same as Scenario 2 and Scenario 1

   Scenario 5 : with proper hashcode method implementation and  Equals method returns true always
            The Keys and values are added to the maps, no duplicates added
            and the last inserted duplicate value added to the map on the existing key.
            Value retreival are working fine because of no duplicate key added
            Can see the get method return all values fine.

  Scenario 6 : with hashcode always return a contstant as 1 and equals always return as false.
            its work like a Scenario 1 and 2

 Scenario 7 : with hashcode always return a constant as 1 and equals always return as true.
            its a magical scenario, it gives the following out put
                Key-> : MyBean{name='AA', id=1, check=CC}  Value -> :22
                #################### sIZE -> 1
                22
                22
                22
              Which means the whenever the same hashcode return during insert, it will check the equals method of the key
              if the equals method return true, wich means the same key so that it replaces the value on the existing key,
              the key we added at first. So the last added value will be set to the first key. And only one key value pair
              exist.

 Scenario 8 : with proper equals and hashcode method, but new property added in bean but not included for hascode and equals.
                This case, the newly added duplicate key is ignored and it just update the latest value to the existing key.

 */

public class HashmapWork {

    public static void main(String[] args) {

        HashMap hashMap = new HashMap(10);

        hashMap.put(new MyBean("AA", 1, "CC"), "1");
        hashMap.put(new MyBean("BB", 2), "2 ");
        hashMap.put(new MyBean("CC", 3), "3");
        hashMap.put(new MyBean("DD", 4), "4");
        hashMap.put(new MyBean("EE", 5), "5");
        hashMap.put(new MyBean("FF", 6), "6");
        hashMap.put(new MyBean("GG", 7), "7");
        hashMap.put(new MyBean("HH", 8), "8");
        hashMap.put(new MyBean("II", 9), "9");
        hashMap.put(new MyBean("II", 9), "99");
        hashMap.put(new MyBean("II", 9, "II 999"), "999");
        hashMap.put(new MyBean("JJ", 10), "10");
        hashMap.put(new MyBean("KK", 11), "11");
        hashMap.put(new MyBean("KK", 11), "111");
        hashMap.put(new MyBean("KK", 11, "KK 1111"), "1111");
        hashMap.put(new MyBean("LL", 12), "12");
        hashMap.put(new MyBean("MM", 13), "13");
        hashMap.put(new MyBean("NN", 14), "14");
        hashMap.put(new MyBean("OO", 15), "15");
        hashMap.put(new MyBean("PP", 16), "16");
        hashMap.put(new MyBean("QQ", 17), "17");
        hashMap.put(new MyBean("RR", 18), "18");
        hashMap.put(new MyBean("SS", 19), "19");
        hashMap.put(new MyBean("TT", 20), "20");
        hashMap.put(new MyBean("UU", 21), "21");
        hashMap.put(new MyBean("VV", 22), "22");


        hashMap.forEach((k, v) -> System.out.println("Key-> : "+k +"  Value -> :" +v ) );

        System.out.println("####################"+ hashMap.size());
        System.out.println(hashMap.get(new MyBean("KK", 11)));
        System.out.println(hashMap.get(new MyBean("VV", 22)));
        System.out.println(hashMap.get(new MyBean("UU", 21)));
    }

}

class MyBean{
    public MyBean(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public MyBean(String name, Integer id, String check) {
        this.name = name;
        this.id = id;
        this.check = check;
    }

    private String name;
    private Integer id;
    private String check;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



 /*  @Override
    public boolean equals(Object o) {
        return  true;
    }


  @Override
  public int hashCode() {
  return 1;
  }*/


  @Override
  public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof MyBean)) return false;

      MyBean myBean = (MyBean) o;

      if (name != null ? !name.equals(myBean.name) : myBean.name != null) return false;
      return id != null ? id.equals(myBean.id) : myBean.id == null;
  }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", check=" + check +
                '}';
    }
}

