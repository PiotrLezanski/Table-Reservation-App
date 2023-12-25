package com.example.application.views;

import com.example.application.views.customer.CustomerSignUpView;
import com.example.application.views.owner.OwnerSignUpView;

public class SignUpViewFactory
{
    public SignUpViewFactory() {}
    
    public CustomerSignUpView createCustomerSignUpView()
    {
        return new CustomerSignUpView();
    }

    public OwnerSignUpView createOwnerSignUpView()
    {
        return new OwnerSignUpView();
    }
}
