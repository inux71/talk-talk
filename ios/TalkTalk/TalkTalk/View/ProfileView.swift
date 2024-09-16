//
//  ProfileView.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 27/08/2024.
//

import SwiftUI

struct ProfileView: View {
    @StateObject private var _viewModel: ProfileViewModel = ProfileViewModel()
    
    var body: some View {
        VStack {
            AsyncImage(url: URL(string: _viewModel.profileImageUrl)) { phase in
                if let image = phase.image {
                    image
                } else if phase.error != nil {
                    Color.gray
                } else {
                    ProgressView()
                }
            }.frame(
                width: 128,
                height: 128
            )
            .clipShape(Circle())
            
            Button(
                "Change", 
                action: {}
            )
            
            List {
                Section {
                    Text(_viewModel.email)
                    
                    SecureField(
                        text: $_viewModel.password,
                        prompt: Text("Password")
                    ) {
                        Text("Password")
                    }
                }
                
                Section {
                    Button(
                        role: .destructive,
                        action: {}
                    ) {
                        Text("Sign out")
                    }
                }
            }
        }.navigationTitle("Kacper Grabiec")
            .background(.background.secondary)
    }
}

#Preview {
    NavigationStack {
        ProfileView()
    }
}
