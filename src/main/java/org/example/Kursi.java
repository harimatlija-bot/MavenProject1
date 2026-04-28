package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Kursi {
    private Integer id;
    private String emriKursit;
    private String kohezgjatja;
    private String programmingLanguage;
}