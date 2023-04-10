package ge.ibsu.demo.controllers;

import ge.ibsu.demo.Services.CustomerService;
import ge.ibsu.demo.dto.Request.RequestData;
import ge.ibsu.demo.dto.SearchCustomer;
import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = {"application/json"})
    public List<Customer> getAll(){
        return customerService.getAllCustomer();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public Customer getById(@PathVariable Long id) throws Exception{
        return customerService.getCustomerById(id);
    }
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = {"application/json"})
    public Slice<Customer> search(@RequestBody RequestData<SearchCustomer> rd){
        return customerService.search(rd.getData(), rd.getPaging());
    }
}
