//
//  LoginView.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 26/08/2024.
//

import SwiftUI

struct LoginView: View {
    @StateObject private var _viewModel: LoginViewModel = LoginViewModel()
    
    var body: some View {
        NavigationStack {
            GeometryReader { geometryProxy in
                VStack(alignment: .center) {
                    Text("Talk Talk")
                    
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
                    
                    NavigationLink {
                        TalkListView()
                    } label: {
                        Text("Sign in")
                            .frame(maxWidth: .infinity)
                    }.buttonStyle(BorderedProminentButtonStyle())
                    
                    HStack(alignment: .center) {
                        Text("Don't have an account?")
                        
                        NavigationLink {
                            RegisterView()
                        } label: {
                            Text("Sign up")
                        }
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
}

#Preview {
    LoginView()
}
