package doan._java_food.common;

import lombok.Value;

@Value
public class BusinessErrorCode {
  String status = "error";
  int code;
  String group;
  String message;
  int httpStatus;
}
