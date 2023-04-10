package ge.ibsu.demo.Services;

import ge.ibsu.demo.dto.Request.Paging;
import ge.ibsu.demo.dto.SearchCustomer;
import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) throws Exception{
        return customerRepository.findById(id).orElseThrow(()-> new Exception("RECORD_NOT_FOUND"));
    }

    public Slice<Customer> search(SearchCustomer searchCustomer, Paging paging) {
        String searchText = null;
        if(searchCustomer.getName() != null && !searchCustomer.equals("")){
            searchText = "%" + searchCustomer.getName() + "%";
        }
        Pageable pageable = PageRequest.of(paging.getPage(), paging.getSize(), Sort.by("createDate").descending());

        return customerRepository.search(searchCustomer.getActive(), searchText, pageable);
    }
}
