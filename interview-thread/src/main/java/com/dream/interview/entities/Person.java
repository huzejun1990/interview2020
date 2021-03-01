package com.dream.interview.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by huzejun
 * on 2020/6/6 17:53
 */

@NoArgsConstructor
@Getter
@Setter
public class Person {

    private Integer id;
    private String personName;

    public Person(String personName) {
        this.personName = personName;
    }
}
