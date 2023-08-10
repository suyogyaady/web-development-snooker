//package com.system.snooker;
//
//import com.system.snooker.Repo.SwimmingpoolRepo;
//import com.system.snooker.entity.Swimmingpool;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.core.annotation.Order;
//
//@DataJpaTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class FutsalRepositoryTest {
//    @Autowired
//    private SwimmingpoolRepo futsalRepo;
//
//
//    @Test
//    @Order(1)
//    public void savefutsalTest() {
//
//        Swimmingpool futsal = Swimmingpool.builder()
//                .futsalname("rak")
//                .futsalcontact("123454")
//                .futsalprice("98888888")
//                .build();
//
//
//        futsalRepo.save(futsal);
//
//        Assertions.assertThat(futsal.getFut_salId()).isGreaterThan(0);
//    }
//
//    @Test
//    @Order(4)
//    public void updatefutsalTest(){
//
//        Swimmingpool futsal = Swimmingpool.builder()
//                .futsalname("rak")
//                .futsalcontact("123454")
//                .futsalprice("98888888")
//                .build();
//
//
//        futsalRepo.save(futsal);
//
//        Swimmingpool futsal1  = futsalRepo.findById(futsal.getFut_salId()).get();
//
//        futsal1.setFutsalcontact("13265");
//
//        Swimmingpool futsalupdated  = futsalRepo.save(futsal);
//
//        Assertions.assertThat(futsalupdated.getFutsalcontact()).isEqualTo("85207410");
//
//    }
//}