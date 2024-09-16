//
//  NewGroupTalkSheet.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 16/09/2024.
//

import SwiftUI

struct NewGroupTalkSheet: View {
    @StateObject private var _viewModel: NewGroupTalkViewModel = NewGroupTalkViewModel()
    
    var body: some View {
        List(_viewModel.profiles, selection: $_viewModel.selectedProfilesIds) { profile in
            ProfileLabel(
                profileName: profile.name,
                profileImageURL: profile.imageURL
            )
        }.navigationTitle("New group talk")
            .searchable(
                text: $_viewModel.searchQuery,
                prompt: "Search"
            ).environment(\.editMode, .constant(.active))
            .toolbar {
                NavigationLink {
                    TalkView(id: 1)
                } label: {
                    Text("Create")
                }.disabled(_viewModel.createButtonDisabled)
            }
    }
}

#Preview {
    NavigationStack {
        NewGroupTalkSheet()
    }
}
