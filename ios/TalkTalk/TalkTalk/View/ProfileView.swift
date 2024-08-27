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
        Text("Profile view")
    }
}

#Preview {
    ProfileView()
}
