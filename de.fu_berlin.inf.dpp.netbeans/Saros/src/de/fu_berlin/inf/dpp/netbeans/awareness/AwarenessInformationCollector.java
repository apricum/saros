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
package de.fu_berlin.inf.dpp.netbeans.awareness;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import de.fu_berlin.inf.dpp.netbeans.editor.EditorManager;
import de.fu_berlin.inf.dpp.netbeans.editor.RemoteEditorManager;
import de.fu_berlin.inf.dpp.session.ISarosSession;
import de.fu_berlin.inf.dpp.session.ISarosSessionManager;
import de.fu_berlin.inf.dpp.session.User;

/**
 * Singleton that provides methods to collect and retrieve awareness information
 * for session participants (who is following who, which file is currently
 * opened, etc.)
 * 
 * All methods provided by the interface are <b>not</b> thread safe.
 * 
 * @author waldmann
 */
public class AwarenessInformationCollector {

    private final EditorManager editorManager;
    private final ISarosSessionManager sessionManager;

    /**
     * Who is following who in the session?
     */
    private final Map<User, User> followModes = new ConcurrentHashMap<User, User>();

    public AwarenessInformationCollector(ISarosSessionManager sessionManager,
        final EditorManager editorManager) {

        this.sessionManager = sessionManager;
        this.editorManager = editorManager;
    }

    /**
     * Make sure to call this, when a session ends, or when a session starts to
     * avoid having outdated information
     */
    public void flushFollowModes() {
        followModes.clear();
    }

    /**
     * Remember that "user" is following "target" in the currently running
     * session.
     * 
     * @param user
     * @param target
     */
    public void setUserFollowing(User user, User target) {
        assert user != null;
        assert !(user.equals(target));

        followModes.remove(user);

        if (target != null) // null is not allowed in CHM
            followModes.put(user, target);
    }

    /**
     * Returns the followee of the given user, or <code>null</code> if that user
     * does not follow anyone at the moment, or there is no active session.
     * 
     * @param user
     * @return
     */
    public User getFollowedUser(User user) {
        assert user != null;

        final ISarosSession session = sessionManager.getSarosSession();

        // should not be called outside of a running session
        if (session == null)
            return null;

        final User followee = followModes.get(user);

        if (followee == null)
            return null;

        /*
         * FIXME this should not be done here, it should be the responsibility
         * of the class that calls setUserFollowing to correctly clear this map
         * entries !
         */
        return session.getUser(followee.getJID());
    }

    /**
     * Checks if the currently active editor of the given user is shared. The
     * user can be the local or remote one.
     * 
     * @return <code>true</code>, if the active editor of the given user is
     *         shared, <code>false</code> otherwise
     */
    public boolean isActiveEditorShared(User user) {
        boolean editorActive = false;

        RemoteEditorManager rem = editorManager.getRemoteEditorManager();
        if (rem != null && user != null) {
//            if (user.isLocal() && editorManager.isActiveEditorShared()
//                || rem.isRemoteActiveEditorShared(user)) {
//                editorActive = true;
//            }
        }
        return editorActive;
    }
}