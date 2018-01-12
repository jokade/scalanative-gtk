---
title: Status of GObject bindings
keywords:
summary:
sidebar: sngtk_sidebar
permalink: sngtk_status_gobject.html
folder: sngtk
datatable: true
missing: <i class="fa fa-close" style="color:red"></i>
ok: <i class="fa fa-check" style="color:green"></i>
partial: <i class="fa fa-adjust" style="color:orange"></i>
---

<div class="datatable-begin"></div>

| Header                      | Status           | Scala modules     | Notes |
|-----------------------------|------------------|-------------------|-------|
| gbinding.h                  | {{page.missing}} |                   |       |
| gboxed.h                    | {{page.missing}} |                   |       |
| gclosure.h                  | {{page.partial}} | `gobject`         | Types provided in package object `gobject`, the rest is not implemented      |
| genums.h                    | {{page.missing}} |                   |       |
| glib-types.h                | {{page.missing}} |                   |       |
| gmarshal.h                  | {{page.missing}} |                   |       |
| gobject-autocleanups.h      | {{page.missing}} |                   |       |
| gobject.h                   | {{page.missing}} |                   |       |
| gobjectnotifyqueue.c        | {{page.missing}} |                   |       |
| gparam.h                    | {{page.missing}} |                   |       |
| gparamspecs.h               | {{page.missing}} |                   |       |
| gsignal.h                   | {{page.partial}} | `GSignalReceiver` |       |
| gsourceclosure.h            | {{page.missing}} |                   |       |
| gtype.h                     | {{page.missing}} |                   |       |
| gtypemodule.h               | {{page.missing}} |                   |       |
| gtypeplugin.h               | {{page.missing}} |                   |       |
| gvalue.h                    | {{page.missing}} |                   |       |
| gvaluearray.h               | {{page.missing}} |                   |       |
| gvaluecollector.h           | {{page.missing}} |                   |       |
| gvaluetypes.h               | {{page.missing}} |                   |       |

         
<div class="datatable-end"></div>

{% include links.html %}
