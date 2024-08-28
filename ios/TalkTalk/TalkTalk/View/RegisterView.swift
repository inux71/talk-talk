//
//  RegisterView.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 27/08/2024.
//

import SwiftUI

struct RegisterView: View {
    @StateObject private var _viewModel: RegisterViewModel = RegisterViewModel()
    
    @FocusState private var _firstnameTextFieldFocused: Bool
    @FocusState private var _lastnameTextFieldFocused: Bool
    @FocusState private var _emailTextFieldFocused: Bool
    @FocusState private var _passwordTextFieldFocused: Bool
    
    var body: some View {
        GeometryReader { geometryProxy in
            VStack(alignment: .center) {
                Text("Talk Talk")
                
                TextField(
                    "First name",
                    text: $_viewModel.firstname,
                    prompt: Text("First name")
                )
                .focused($_firstnameTextFieldFocused)
                
                TextField(
                    "Last name",
                    text: $_viewModel.lastname,
                    prompt: Text("Last name")
                )
                .focused($_lastnameTextFieldFocused)
                
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
                    Text("Sign up")
                        .frame(maxWidth: .infinity)
                }
                .buttonStyle(BorderedProminentButtonStyle())
                
                HStack(alignment: .center) {
                    Text("Already have an account?")
                    
                    Button(
                        "Sign in",
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
    RegisterView()
}
