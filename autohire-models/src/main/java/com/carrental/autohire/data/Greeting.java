package com.carrental.autohire.data;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("Greeting")
@ToString
public class Greeting {
  private String name;
  private String msg;
}
