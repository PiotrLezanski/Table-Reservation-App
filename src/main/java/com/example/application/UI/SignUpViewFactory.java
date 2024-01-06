package com.example.application.UI;

import com.example.application.UI.customer.CustomerSignUpView;
import com.example.application.UI.owner.OwnerSignUpView;

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
