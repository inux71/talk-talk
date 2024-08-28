//
//  LoginView.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 26/08/2024.
//

import SwiftUI

struct LoginView: View {
    @StateObject private var _viewModel: LoginViewModel = LoginViewModel()
    
    @FocusState private var _emailTextFieldFocused: Bool
    @FocusState private var _passwordTextFieldFocused: Bool
    
    var body: some View {
        GeometryReader { geometryProxy in
            VStack(alignment: .center) {
                Text("Talk Talk")
                
                TextField(
                    "Email",
                    text: $_viewModel.email,
                    prompt: Text("Email")
                )
                .focused($_emailTextFieldFocused)
                
                SecureField(
                    "Password",
                    text: $_viewModel.password,
                    prompt: Text("Password")
                )
                .focused($_passwordTextFieldFocused)
                
                Button(action: {}) {
                    Text("Sign in")
                        .frame(maxWidth: .infinity)
                }
                .buttonStyle(BorderedProminentButtonStyle())
                
                HStack(alignment: .center) {
                    Text("Don't have an account?")
                    
                    Button(
                        "Sign up",
                        action: {}
                    )
                }
                .buttonStyle(DefaultButtonStyle())
            }
            .frame(
                width: geometryProxy.size.width * 0.7,
                alignment: .center
            )
            .frame(
                maxWidth: .infinity,
                maxHeight: .infinity,
                alignment: .center
            )
            .textFieldStyle(RoundedBorderTextFieldStyle())
            .textInputAutocapitalization(.never)
            .autocorrectionDisabled()
        }
    }
}

#Preview {
    LoginView()
}
