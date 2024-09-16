//
//  RegisterView.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 27/08/2024.
//

import SwiftUI

struct RegisterView: View {
    @Environment(\.dismiss) private var dismiss
    @StateObject private var _viewModel: RegisterViewModel = RegisterViewModel()
    
    var body: some View {
        GeometryReader { geometryProxy in
            VStack(alignment: .center) {
                Text("Talk Talk")
                
                TextField(
                    "First name",
                    text: $_viewModel.firstname,
                    prompt: Text("First name")
                )
                
                TextField(
                    "Last name",
                    text: $_viewModel.lastname,
                    prompt: Text("Last name")
                )
                
                TextField(
                    "Email",
                    text: $_viewModel.email,
                    prompt: Text("Email")
                )
                
                SecureField(
                    "Password",
                    text: $_viewModel.password,
                    prompt: Text("Password")
                )
                
                Button(action: {
                    dismiss()
                }) {
                    Text("Sign up")
                        .frame(maxWidth: .infinity)
                }
                .buttonStyle(BorderedProminentButtonStyle())
                
                HStack(alignment: .center) {
                    Text("Already have an account?")
                    
                    Button(
                        "Sign in",
                        action: {
                            dismiss()
                        }
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
        }.navigationBarBackButtonHidden()
    }
}

#Preview {
    RegisterView()
}
