package com.example.application.views.login;

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
