package com.spring.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by akhil on 30/3/17.
 */
@Service
public class TempService {
    @Transactional
    void transferToDummyTableWithRequiredNew(){
        int i=1/0;
    }
}
