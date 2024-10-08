//
//  NewTalkSheet.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 16/09/2024.
//

import SwiftUI

struct NewTalkSheet: View {
    @AppStorage(UserDefaultsKeys.DARK_THEME) private var _darkTheme: Bool = false
    @StateObject private var _viewModel: NewTalkViewModel = NewTalkViewModel()
    
    var body: some View {
        NavigationStack {
            List {
                NavigationLink {
                    NewGroupTalkSheet()
                } label: {
                    Label("New group talk", systemImage: "person.3.fill")
                        .labelStyle(.titleAndIcon)
                }
                
                Section {
                    ForEach(_viewModel.profiles) { profile in
                        NavigationLink {
                            // first check if talk exists, profile.id != talk.id, just for now
                            TalkView(id: 1)
                        } label: {
                            ProfileLabel(
                                profileName: "Kacper Grabiec",
                                profileImageURL: URL(string: "https://scontent-waw2-2.xx.fbcdn.net/v/t39.30808-1/406051853_3340379186261131_8985740721870838088_n.jpg?stp=cp6_dst-jpg_s480x480&_nc_cat=109&ccb=1-7&_nc_sid=0ecb9b&_nc_ohc=v97YlMmqj64Q7kNvgFG1f1S&_nc_ht=scontent-waw2-2.xx&oh=00_AYCWlbfSDXKM7rpbk4DPKpD6ncVaWqbxgnetuoShozKJ5w&oe=66CCDFBA")!
                            )
                        }
                    }
                }
            }.navigationTitle("New talk")
                .searchable(
                    text: $_viewModel.searchQuery,
                    prompt: "Search"
                )
        }.preferredColorScheme(_darkTheme ? .dark : .light)
    }
}

#Preview {
    NewTalkSheet()
}
