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
package de.fu_berlin.inf.dpp.netbeans.ui.eventhandler;

import de.fu_berlin.inf.dpp.netbeans.concurrent.watchdog.IsInconsistentObservable;
import de.fu_berlin.inf.dpp.netbeans.ui.util.SWTUtils;
import de.fu_berlin.inf.dpp.netbeans.ui.util.ViewUtils;
import de.fu_berlin.inf.dpp.observables.ValueChangeListener;

/**
 *
 * @author privateuser
 */
public class SessionViewOpener {
    public SessionViewOpener(IsInconsistentObservable isInconsistentObservable) {
        isInconsistentObservable.add(new ValueChangeListener<Boolean>() {
            @Override
            public void setValue(Boolean inconsistency) {
                if (!inconsistency) {
                    return;
                }

                SWTUtils.runSafeSWTAsync(null, new Runnable() {
                    @Override
                    public void run() {
                        ViewUtils.openSarosView();
                    }
                });
            }
        });
    }
}
