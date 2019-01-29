package ru.job4j.list;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserConvert {
    public HashMap<Integer, User> process(List<User> list) {
    //    HashMap<Integer, User> map = new HashMap<>();
    //    for (User user : list) {
    //        map.put(user.getId(), user);
    //    }
    //    return map;
       return (HashMap<Integer, User>) list.stream()
               .collect(Collectors
                       .toMap(User::getId, Function.identity(), (oldValue, newValue) -> newValue));
    }
}
