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
                Text("app-name")
                
                TextField(
                    "email",
                    text: $_viewModel.email,
                    prompt: Text("email-prompt")
                )
                .focused($_emailTextFieldFocused)
                
                SecureField(
                    "password",
                    text: $_viewModel.password,
                    prompt: Text("password-prompt")
                )
                .focused($_passwordTextFieldFocused)
                
                Button(action: {}) {
                    Text("sign-in")
                        .frame(maxWidth: .infinity)
                }
                .buttonStyle(BorderedProminentButtonStyle())
                
                HStack(alignment: .center) {
                    Text("no-account")
                    
                    Button(
                        "sign-up",
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
