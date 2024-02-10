package com.example.application.UI.signup;

import com.example.application.UI.customer.signup.CSignUpView;
import com.example.application.UI.owner.signup.OSignUpView;

public class SignUpViewFactory
{
    public SignUpViewFactory() {}
    
    public CSignUpView createCustomerSignUpView()
    {
        return new CSignUpView();
    }

    public OSignUpView createOwnerSignUpView()
    {
        return new OSignUpView();
    }
}
