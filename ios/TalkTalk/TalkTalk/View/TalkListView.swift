//
//  TalkListView.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 27/08/2024.
//

import SwiftUI

struct TalkListView: View {
    @StateObject private var _viewModel: TalkListViewModel = TalkListViewModel()
    
    var body: some View {
        List {
            Picker("Talk type", selection: $_viewModel.selectedTalkType) {
                ForEach(TalkType.allCases, id: \.self) { talkType in
                    Text(talkType.rawValue)
                }
            }.pickerStyle(.segmented)
            
            ForEach(_viewModel.talks) { talk in
                NavigationLink {
                    TalkView(id: talk.id)
                } label: {
                    TalkHeader(
                        talkName: talk.name,
                        lastMessageSenderName: "You",
                        lastMessage: "Ok",
                        lastMessageDate: Date(),
                        muted: false,
                        blocked: false,
                        unread: false,
                        talkImageUrl: talk.imageURL
                    )
                }//.badge($0)
                    .swipeActions {
                        Button(
                            role: .destructive,
                            action: {}
                        ) {
                            Label(
                                "Delete",
                                systemImage: "trash.fill"
                            )
                        }
                        
                        Menu(
                            "Options",
                            systemImage: "ellipsis"
                        ) {
                            Button(action: {}) {
                                Label(
                                    "Mute",
                                    systemImage: "bell.slash.fill"
                                )
                            }
                            
                            Button(action: {}) {
                                Label(
                                    "Block",
                                    systemImage: "nosign"
                                )
                            }
                        }.labelStyle(.titleAndIcon)
                    }
            }.onDelete { indexSet in
                // To do
            }
        }.navigationTitle("Talks")
            .navigationBarTitleDisplayMode(.large)
            .navigationBarBackButtonHidden()
            .toolbar {
                ToolbarItemGroup {
                    NavigationLink {
                        ProfileView()
                    } label: {
                        Label(
                            "Profile",
                            systemImage: "person.crop.circle"
                        )
                    }
                    
                    NavigationLink {
                        SettingsView()
                    } label: {
                        Label(
                            "Settings",
                            systemImage: "gear"
                        )
                    }
                    
                    Button(
                        "New talk",
                        systemImage: "plus.circle",
                        action: {
                            _viewModel.newTalkSheetPresented.toggle()
                        }
                    ).sheet(
                        isPresented: $_viewModel.newTalkSheetPresented,
                        onDismiss: {}
                    ) {
                        NewTalkSheet()
                    }
                }
            }.searchable(
                text: $_viewModel.searchQuery,
                prompt: "Search"
            )
    }
}

#Preview {
    NavigationStack {
        TalkListView()
    }
}
