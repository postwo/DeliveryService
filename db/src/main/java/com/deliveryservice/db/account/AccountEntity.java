package com.deliveryservice.db.account;

import com.deliveryservice.db.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@SuperBuilder//부모로부터 상속 받은변수도 포함시키겠다 == 부모가 가진 변수도 사용할수 있다
@Data
@EqualsAndHashCode(callSuper = true) //객체 비교할때 사용된다 //false = 자기자신만 // true = 클래스의 equals와 hashCode 메소드를 생성할 때 상속받은 클래스의 필드도 포함되도록 하는 설정
@Entity
@Table(name ="account")
public class AccountEntity extends BaseEntity {

}
