/*
 * Copyright (C) 2016 privateuser
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package de.fu_berlin.inf.dpp.netbeans.editor;

import de.fu_berlin.inf.dpp.activities.SPath;
import de.fu_berlin.inf.dpp.editor.IEditorManager;
import de.fu_berlin.inf.dpp.editor.ISharedEditorListener;
import de.fu_berlin.inf.dpp.filesystem.IProject;
import de.fu_berlin.inf.dpp.session.AbstractActivityProducer;
import java.util.Set;

/**
 *
 * @author privateuser
 */
public class EditorManager extends AbstractActivityProducer implements
    IEditorManager{
    private RemoteEditorManager remoteEditorManager = null;
    
    public RemoteEditorManager getRemoteEditorManager() {
         return this.remoteEditorManager;
     }

    @Override
    public Set<SPath> getLocallyOpenEditors() {
        return null;
    }

    @Override
    public Set<SPath> getRemotelyOpenEditors() {
        return null;
    }

    @Override
    public String getContent(SPath spath) {
        return null;
    }

    @Override
    public void saveEditors(IProject ip) {
        
    }

    @Override
    public void addSharedEditorListener(ISharedEditorListener il) {
        
    }

    @Override
    public void removeSharedEditorListener(ISharedEditorListener il) {
        
    }
}
