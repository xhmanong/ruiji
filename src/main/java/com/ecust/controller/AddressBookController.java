package com.ecust.controller;

import com.ecust.pojo.AddressBook;
import com.ecust.pojo.R;
import com.ecust.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author solang
 * @date 2023-06-11 10:48
 */
@RestController
@Slf4j
@RequestMapping("/addressBook")
public class AddressBookController {
    @Autowired
    private AddressBookService addressBookService;

    @GetMapping("/list")
    public R list() {
        List<AddressBook> addressBookList=addressBookService.list();
        return R.success(addressBookList);
    }

    @Transactional
    @PutMapping("/default")
    public R setDefault(HttpServletRequest request, @RequestBody AddressBook addressBook){
        log.info("设为默认接收到的数据是：{}",addressBook);
        addressBookService.setDefault(request,addressBook);
        return R.success("默认地址设置成功");
    }

    @PostMapping
    public R addAddress(HttpServletRequest request,@RequestBody AddressBook addressBook){
        log.info("新增地址接收到的数据是：{}",addressBook);
        addressBookService.addAddress(request,addressBook);
        return R.success("新增成功");
    }
    @GetMapping("/default")
    public R getDefault(){
        AddressBook addressBook=addressBookService.getDefault();
        return  R.success(addressBook);
    }
}
