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
        Text("Talk list view")
    }
}

#Preview {
    TalkListView()
}
