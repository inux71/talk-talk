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
                Text("app-name")
                
                TextField(
                    "firstname",
                    text: $_viewModel.firstname,
                    prompt: Text("firstname-prompt")
                )
                .focused($_firstnameTextFieldFocused)
                
                TextField(
                    "lastname",
                    text: $_viewModel.lastname,
                    prompt: Text("lastname-prompt")
                )
                .focused($_lastnameTextFieldFocused)
                
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
                    Text("sign-up")
                        .frame(maxWidth: .infinity)
                }
                .buttonStyle(BorderedProminentButtonStyle())
                
                HStack(alignment: .center) {
                    Text("already-have-account")
                    
                    Button(
                        "sign-in",
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
